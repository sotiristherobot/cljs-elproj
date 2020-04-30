(ns frontend.events
  (:require [re-frame.core :refer [reg-event-db reg-sub]]))

;; EVENTS SECTION

;; initial app-state
(reg-event-db 
 :initialize-app-state
 (fn [_ _] {:name "Sotiris" :last "Ioannou"}))

;; registers an event - action - that changes the :last symbol to something
(reg-event-db
 :change-last
 (fn [state new-value]
   (assoc state :last new-value)))

;; SUBSCRIPTIONS SECTION
;; registers a subscription that returns the lastname from the app-state
(reg-sub
 :initialize-db
 (fn [state _]
   (:last state)))
