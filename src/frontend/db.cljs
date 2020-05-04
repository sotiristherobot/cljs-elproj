(ns frontend.db
  (:require [re-frame.core :as rf]))

(def initial-app-state 
  {
   :user {
          :is-authorized false
          :first "Sotiris"
          :username "sot"
          :last "Ioannou"}
   :posts nil})
