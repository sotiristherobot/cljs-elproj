(ns frontend.renderer.core
  (:require [reagent.core :as reagent]
            [frontend.authorization.core :refer [form]]))


(defn hello-world [] 
  [:div
   [form]])

(defn start! []
  (reagent/render
   [hello-world]
   (js/document.getElementById "app-container")))
(start!)
