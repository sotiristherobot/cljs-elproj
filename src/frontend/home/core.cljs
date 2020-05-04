(ns frontend.home.core
  (:require [re-frame.core :as rf :refer [subscribe dispatch]]))


(defn generate-menu-list [menu-items]
      [:div
       [:ul {:style {:paddingLeft "20px" :margin "0px"}}
        (map (fn [item]
                 [:li [:a item]])
             menu-items)]])

(defn side-bar [username]
      [:div {:style {:margin "20px" :display "flex" :maxWidth "350px" :flexGrow 1
                     :flexDirection "column"}}
       [:h4 (str "Hello " username)]
       [generate-menu-list ["Home" "About"]]])

(defn render-posts []
  "Render posts"
  (let [posts @(subscribe [:get-posts])]
    [:div (str posts)]))


;; temporary function to check re-frame functionality
(defn home[user-info]
  (println "Rerendering home-new")
  (let [name (:first user-info)]
    (dispatch [:fetch-posts])
    [:div {:style {:display "flex"}}
     [side-bar name]
     [:div {:style {:margin "20px" :display "flex" :flexGrow 1}}
      [:div
       [:h4 "Home Component"]
       [render-posts]]]]))

