(ns frontend.simplerouter.core)

(defn get-path 
  "Returns the current path from window"
  []
  (. js/window.location -pathname))

(defn set-path!
  "Sets a new path if it's not already there"
  [new-location]
  (if-not (= (get-path) new-location)
      (.pushState js/window.history "" "" new-location)))