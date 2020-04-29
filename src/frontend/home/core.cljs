(ns frontend.home.core

  )
(defn side-bar [username]
      [:div {:style {:margin "20px" :display "flex" :maxWidth "350px" :flexGrow 1}}
       [:h4 (str "Hello " username)]])

(defn home-container [state]
      (let [username (get @state :username)]
           [:div {:style {:display "flex"}}
            [side-bar username]
            [:div {:style {:margin "20px" :display "flex" :flexGrow 1}} [:h4 "Home Component"]]]))
