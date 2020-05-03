(ns frontend.subs
  (:require [re-frame.core :refer [reg-sub]]))

;; SUBSCRIPTIONS SECTION

;; registers a subscription that returns the lastname from the app-state
(reg-sub :is-user-authorized? (fn [state _] 
                                 (get-in state [:user :is-authorized])))

;; returns the user map
(reg-sub :get-user (fn [state _] (get-in state [:user])))
