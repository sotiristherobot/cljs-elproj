(ns frontend.renderer.core
  (:require [reagent.core :as reagent]))

(defn hello-world
  [] [:div [:p "Kosrakis"]]
  )

(defn start! []
  (js/console.log test)
  (reagent/render
   [hello-world]
   (js/document.getElementById "app-container")))

(start!)
