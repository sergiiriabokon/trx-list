(ns trx-list.db)

(def default-db
  {:name "re-frame"
   :trx-list [{:from "Johny Doe"
               :from-account "1234-1423-5333-2222"
               :to "Brian Gold"
               :to-account "3332-2232-3444-2378"
               :amount 234.53
               :currency "UAH"}
              
              {:from "Jean Apple"
               :from-account "1234-1423-5333-2222"
               :to "Giovanni Mol"
               :to-account "1132-2982-3004-2008"
               :amount 300.0
               :currency "UAH"}
              
              {:from "Josh Big"
               :from-account "1884-1403-5243-3372"
               :to "Greg Sonwalker"
               :to-account "9152-2080-3414-8008"
               :amount 730.0
               :currency "UAH"}]})
