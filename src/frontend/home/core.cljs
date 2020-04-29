(ns frontend.home.core)


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



(defn home-container [state]
      (let [username (get @state :username)]
           [:div {:style {:display "flex"}}
            [side-bar username]
            [:div {:style {:margin "20px" :display "flex" :flexGrow 1}} [:h4 "Home Component"]]]))
