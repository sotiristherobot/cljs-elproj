(ns frontend.renderer.core
  (:require [reagent.core :as reagent :refer [atom cursor]]
            [frontend.authorization.core :refer [form]]
            [frontend.simplerouter.core :refer [get-path set-path!]]
            ))

(defn side-bar [username]
      [:div {:style {:margin "20px"}}
       [:h4 (str "Hello " username)]])

(defn home-container [state]
      (let [username (get @state :username)]
           [:div {:style {:display "flex" :justify-content "center"}}
            [side-bar username]
            [:div {:style {:margin "20px"}} [:h4 "Home Component"]]]))

(defn show-path-component
      "Simple middleware which shows the current path above every component.
       Needs to be changed to be activated only on dev mode"
      [path]
      [:div
       [:h5 (str "Current path is: " path)]])

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
                          [home-container app-state]])))))

(defn start! []
      (reagent/render
        [app]
        (js/document.getElementById "app-container")))
(start!)
