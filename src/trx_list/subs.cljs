(ns trx-list.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::new-trx
 (fn [db]
   (:new-trx db)))

(re-frame/reg-sub
 ::trx-list
 (fn [db]
   (:trx-list db)))

(re-frame/reg-sub
 ::percentages
 (fn [db]
   (:percentages db)))
