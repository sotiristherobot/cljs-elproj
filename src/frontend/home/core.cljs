(ns frontend.home.core

  )
(defn side-bar [username]
      [:div {:style {:margin "20px"}}
       [:h4 (str "Hello " username)]])

(defn home-container [state]
      (let [username (get @state :username)]
           [:div {:style {:display "flex" :justify-content "center"}}
            [side-bar username]
            [:div {:style {:margin "20px"}} [:h4 "Home Component"]]]))
