(ns frontend.authorization.core
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [dispatch]]
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


(defn form
      "Takes as an input an is-authorized cursor from app-state"
      []
      (let [doc (atom {})]
           (fn []
               [:div
                [:div.page-header [:h1 "Login form"]]
                [bind-fields form-template doc]
                [:button {:on-click (fn []
                                      (dispatch [:authorize-user]))}"Login"]
                [:label (str @doc)]])))
