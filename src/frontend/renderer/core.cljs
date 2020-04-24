(ns frontend.renderer.core
  (:require [reagent.core :as reagent :refer [atom cursor]]
            [frontend.authorization.core :refer [form]]
            [frontend.simplerouter.core :refer [get-path]]
))

; global state of the application
(defonce app-state (atom {:is-authorized true}))

(defn main []
   [:div
    [:h1 "This is the main component"]])

(defn app 
  "app function takes the state as a parameter if the user is not authorized then render a form component and pass as a cursor with
the is-authorized key from state"
  [state]
  (if-not (= (get-in @state [:is-authorized]) true)
    [form (cursor state [:is-authorized])]
    [main]))

(defn show-path-middleware! 
  "Simple middleware which shows the current path above every component. Needs to be changed to be activated only on dev mode"
  [cmpFn]
  (let [path (get-path)]
    [:div
     [:h5 (str "Current path is: " path)]
     [:div cmpFn]]))

(defn start! []
  (reagent/render
   (show-path-middleware! (app app-state))
   (js/document.getElementById "app-container")))
(start!)
