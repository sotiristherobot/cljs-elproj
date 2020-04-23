(ns frontend.renderer.core
  (:require [reagent.core :as reagent :refer [atom]]
            [frontend.authorization.core :refer [form]]))

; global state of the application
(defonce app-state (atom {:is-authorized false}))

(defn main []
  [:div
   [:h1 "This is the main component"]])

(defn app []
  (if-not (= (get-in @app-state [:is-authorized]) true)
    [form]
    [main]
    ))

(defn start! []
  (reagent/render
   [app]
   (js/document.getElementById "app-container")))
(start!)
