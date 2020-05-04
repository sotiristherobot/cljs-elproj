(ns frontend.renderer.core
  (:require [reagent.core :as reagent :refer [atom cursor]]
            [re-frame.core :as rf :refer [subscribe dispatch dispatch-sync]]
            [frontend.events]
            [frontend.subs]
            [frontend.db]
            [frontend.authorization.core :refer [form]]
            [frontend.home.core :refer [home]]
            [frontend.simplerouter.core :refer [get-path set-path!]]
            [ajax.core :refer [GET]]))

;; println writes to console.log
(enable-console-print!)

;; initialize app-state
(dispatch-sync [:initialize-app-state])
 
(defn show-path-component
      "Simple middleware which shows the current path above every component.
       Needs to be changed to be activated only on dev mode"
  [path]
      [:div
       [:h5 (str "Current path is: ")]])

(defn app
      "app function takes the state as a parameter if the user is not
       authorized then render a form component and pass as a cursor with
       the is-authorized key from state"
      []
  (let [is-user-authorized @(subscribe [:is-user-authorized?])]
    (println (str "Home Container is rerendering"))
    (if (true? is-user-authorized)
      (let [user-info @(subscribe [:get-user])]
        [home user-info])
      [form])))

(defn start! []
      (reagent/render
       [app]
        (js/document.getElementById "app-container")))
(start!)

