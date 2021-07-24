(ns trx-list.views
  (:require
   [re-frame.core :as re-frame]
   [trx-list.subs :as subs]
   [trx-list.events :as events]))

(defn calculate-percentages [trx-list]
  (let [amounts  (map #(:amount %) trx-list)
        summmed  (reduce + amounts)
        percentages  (map #(* 100 
                              (/ % summmed)) 
                          amounts)]
    percentages))

(defn format-trx [trx] 
  [:tr {:key (:to-account trx)}
   [:td (:from trx)]
   [:td (:from-account trx)]
   [:td (:to trx)]
   [:td (:to-account trx)]
   [:td (:amount trx) (:currency trx)]
   ])

(defn add-trx-form []
  (let [new-trx (re-frame/subscribe [::subs/new-trx])]
    [:div.new-trx
     [:h2 "New transaction"]
     [:div.control
      [:input.input {:value (:from  @new-trx)
                     :on-change #(re-frame/dispatch [::events/update-from (assoc @new-trx :from (-> % .-target .-value))])
                     :type "text" :placeholder "From" :style {:float "left"} }]]
     [:div.control
      [:input.input {:value (:from-account  @new-trx)
                     :on-change #(re-frame/dispatch [::events/update-from (assoc @new-trx :from-account (-> % .-target .-value))])
                     :type "text" :placeholder "From Account"}]]
     [:div.control
      [:input.input {:value (:to  @new-trx)
                     :on-change #(re-frame/dispatch [::events/update-from (assoc @new-trx :to (-> % .-target .-value))])
                     :type "text" :placeholder "To" :style {:float "left"}}]]
     [:div.control
      [:input.input {:value (:to-account  @new-trx)
                     :on-change #(re-frame/dispatch [::events/update-from (assoc @new-trx :to-account (-> % .-target .-value))])
                     :type "text" :placeholder "To Account"}]]
     [:div.control
      [:input.input {:value (:amount  @new-trx)
                     :on-change #(re-frame/dispatch [::events/update-from (assoc @new-trx :amount (-> % .-target .-value int))])
                     :type "text" :placeholder "Amount" :style {:float "left"}}]]
     [:div.control
      [:input.input {:value (:currency  @new-trx)
                     :on-change #(re-frame/dispatch [::events/update-from (assoc @new-trx :currency (-> % .-target .-value))])
                     :type "text" :placeholder "currency"}]]
     [:button {:on-click
               #(re-frame/dispatch [::events/add-trx @new-trx])} "Add"]]))

(defn add-chartist []
  (let [trx-list (re-frame/subscribe [::subs/trx-list])]
    (print @trx-list)
  (.update js/gaugechart  
                     (clj->js {:series 
                               (calculate-percentages @trx-list)})
                     )))

(defn main-panel []
  (add-chartist)
  (let [trx-list (re-frame/subscribe [::subs/trx-list])]
    [:div
     [:h1
      "Transactions list"]
     
    [:table [:tbody
     [:tr [:th "from"]
          [:th "from account"]
          [:th "to"]
          [:th "to account"]
          [:th "amount"]]
     (map format-trx @trx-list)]]
     (add-trx-form)]))