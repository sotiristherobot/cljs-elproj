(ns frontend.renderer.core
  (:require [reagent.core :as reagent :refer [atom cursor]]
            [re-frame.core :as rf :refer [subscribe dispatch dispatch-sync]]
            [frontend.events]
            [frontend.db]
            [frontend.authorization.core :refer [form]]
            [frontend.home.core :refer [home-container]]
            [frontend.simplerouter.core :refer [get-path set-path!]]))

;; println writes to console.log
(enable-console-print!)

;; initialize app-state
(dispatch-sync [:initialize-app-state])

(defn show-path-component
      "Simple middleware which shows the current path above every component.
       Needs to be changed to be activated only on dev mode"
      [path]
      [:div
       [:h5 (str "Current path is: " )]])


(defn test-component []
  [:div
   [:button {:on-click (fn []
                         (dispatch [:change-last "Something"])
                         )} "Click me"]])

(defn second-text-component []
  (let [last @(subscribe [:initialize-db]) ]
    [:div [:p last]]))

(defn app
      "app function takes the state as a parameter if the user is not
       authorized then render a form component and pass as a cursor with
       the is-authorized key from state"
      []
      (let [app-state (atom {:is-authorized false :username nil})]
           (fn []
               (if-not (= (get-in @app-state [:is-authorized]) true)
                       (do
                         (set-path! "/login")
                         [:div
                          [show-path-component (get-path)]
                          [form
                           (cursor app-state [:is-authorized])
                           (cursor app-state [:username])]])
                       (do
                         (set-path! "/home")
                         [:div
                          [show-path-component (get-path)]
                          ;; these are for test purposes and need to be removed
                          [test-component]
                          [second-text-component]
                          [home-container app-state]])))))

(defn start! []
      (reagent/render
       [app]
        (js/document.getElementById "app-container")))
(start!)
