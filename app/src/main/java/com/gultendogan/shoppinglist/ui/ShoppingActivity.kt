package com.gultendogan.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gultendogan.shoppinglist.R
import com.gultendogan.shoppinglist.data.db.ShoppingDatabase
import com.gultendogan.shoppinglist.data.repositories.ShoppingRepository
import com.gultendogan.shoppinglist.ui.shoppinglist.ShoppingViewModel
import com.gultendogan.shoppinglist.ui.shoppinglist.ShoppingViewModelFactory

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)
    }
}