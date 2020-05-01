(ns frontend.events
  (:require [re-frame.core :refer [reg-event-db]]
            [frontend.db :refer [initial-app-state]]))

;; EVENTS SECTION

;; initial app-state
(reg-event-db 
 :initialize-app-state
 (fn [_ _] 
   (println initial-app-state)
   initial-app-state))

;; authorize user
(reg-event-db
 :authorize-user
 (fn [state _]
   (println "authorizing")
   (assoc-in state [:user :is-authorized] true)))

;; registers an event - action - that changes the :last symbol to something
(reg-event-db
 :change-last
 (fn [state new-value]
   (assoc state :last new-value)))
