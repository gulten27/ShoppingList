package com.gultendogan.shoppinglist.data.repositories

import com.gultendogan.shoppinglist.data.db.ShoppingDatabase
import com.gultendogan.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository(private var db:ShoppingDatabase) {

    suspend fun upsert(item : ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItem() = db.getShoppingDao().getAllShoppingItems()
}