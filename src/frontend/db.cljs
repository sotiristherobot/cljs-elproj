(ns frontend.db
  (:require [re-frame.core :as rf]))

(def initial-app-state 
  {
   :user {
          :is-authorized false
          :first ""
          :username ""
          :last ""}
   :posts nil})
