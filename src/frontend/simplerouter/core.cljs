(ns frontend.simplerouter.core)

(defn get-path 
  "Returns the current path from window"
  []
  (. js/window.location -pathname))

