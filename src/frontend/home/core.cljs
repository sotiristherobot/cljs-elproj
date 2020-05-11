(ns frontend.home.core
  (:require [re-frame.core :as rf :refer [subscribe dispatch]]
            [reagent.core :as r :refer [atom]]))


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

;; print values inside let
;; _(println @post-state)
(defn render-post [post-value _]
  (let [post-state (r/atom {:is-deleting false})
        title (:title post-value)]
    [(fn [post-value]
       (let [
             is-delete-mode (get @post-state :is-deleting)
             post-bgcolor (if (= is-delete-mode true) "yellow" "#EDD1B0")]
         [:div
          {:style {:padding "20px" :margin "0 0 10px 0" :backgroundColor post-bgcolor  :display "flex" :flexDirection "row" :justifyContent "space-between"}}
          [:div {:style {:display "flex" :flexWrap "wrap" :padding "0 10px 0 0"}} title]
          (if (= is-delete-mode false)
            [:button {:on-click (fn [] 
                                 (reset! post-state {:is-deleting true}))} "Delete"]
            [:div
             [:div "Marked for deletion"]
             [:button {:on-click (fn []
                                   (reset! post-state {:is-deleting false})
                                   )} "Undo"]])]))]))

(defn render-posts []
  "Render posts"
  (let [posts @(subscribe [:get-posts])]
    (when posts
      [:div
       (map render-post
            (last posts))])))

;; temporary function to check re-frame functionality
(defn home[user-info]
  (println "Rerendering home-new")
  (dispatch [:fetch-posts])
  (let [name (:first user-info)]
    [:div {:style {:display "flex"}}
     [side-bar name]
     [:div {:style {:margin "20px" :display "flex" :flexGrow 1}}
      [:div
       [:h4 "The Home Component"]
       [render-posts]]]]))

