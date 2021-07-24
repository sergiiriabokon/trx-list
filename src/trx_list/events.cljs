(ns trx-list.events
  (:require
   [re-frame.core :as re-frame]
   [trx-list.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::add-trx
 (fn [db [_ trx]]
   (assoc db
          :trx-list
          (conj (:trx-list db) trx))))

(re-frame/reg-event-db
 ::update-from
 (fn [db [_ val]]
   (assoc db :new-trx 
          val)))

