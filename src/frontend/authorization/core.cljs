(ns frontend.authorization.core
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent-forms.core :refer [bind-fields]]))

(defn row
  "Creates a row for each input element of the form"
  [label input]
  [:div.row
   [:div.col-md-2 [:label label]]
   [:div.col-md-5 input]])

(def form-template
  [:div
   (row "username" [:input {:field :text :id :username}])
   (row "password" [:input {:field :password :id :pass}])])


(defn form []
  (let [doc (atom {})]
    (fn []
      [:div
       [:div.page-header [:h1 "Login form"]]
       [bind-fields form-template doc]
       [:button {:on-click #(js/console.log @doc)} "Login"]
       [:label (str @doc)]])))
