package com.gultendogan.shoppinglist.ui.shoppinglist

import com.gultendogan.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item:ShoppingItem)
}