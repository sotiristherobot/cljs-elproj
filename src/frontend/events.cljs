(ns frontend.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [day8.re-frame.http-fx]
            [ajax.core :as ajax]
            [frontend.db :refer [initial-app-state]]))

;; initial app-state
(reg-event-db
 :initialize-app-state
 (fn [_ _]
   initial-app-state))

;; User authorization
(reg-event-db
 :authorize-user
 (fn [state _]
   (assoc-in state [:user :is-authorized] true)))

;; registers an event - action - that changes the :last symbol to something
(reg-event-db
 :change-last
 (fn [state new-value]
   (assoc state :last new-value)))

(reg-event-db
 :fetch-posts-success
 (fn [state new-posts]
   (assoc state :posts new-posts)))

(reg-event-fx
 :fetch-posts
 (fn [state _]
   {:http-xhrio
    {:method :get
     :uri "https://jsonplaceholder.typicode.com/posts"
     :response-format (ajax/json-response-format {:keywords? true})
     :on-success [:fetch-posts-success]}}))
