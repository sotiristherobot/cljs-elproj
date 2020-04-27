(ns frontend.renderer.core
  (:require [reagent.core :as reagent :refer [atom cursor]]
            [frontend.authorization.core :refer [form]]
            [frontend.simplerouter.core :refer [get-path set-path!]]
            ))

; global state of the application
(defonce app-state (atom {:is-authorized false}))

(defn home []
      [:div
       "Home Component"])

(defn show-path-component
      "Simple middleware which shows the current path above every component. Needs to be changed to be activated only on dev mode"
      [path]
      [:div
       [:h5 (str "Current path is: " path)]])

(defn main [state]
      (if-not (= (get-in @state [:is-authorized]) true)
              (do
                (set-path! "/login")
                [:div
                 [show-path-component (get-path)]
                 [form (cursor state [:is-authorized])]])
              (do
                (set-path! "/home")
                [:div
                 [show-path-component (get-path)]
                 [home]])))

(defn app
      "app function takes the state as a parameter if the user is not authorized then render a form component and pass as a cursor with
    the is-authorized key from state"
      [state]
      [:div
       [main state]])

(defn start! []
      (reagent/render
        (app app-state)
        (js/document.getElementById "app-container")))
(start!)
