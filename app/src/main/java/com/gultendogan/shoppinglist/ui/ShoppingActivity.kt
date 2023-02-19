package com.gultendogan.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gultendogan.shoppinglist.R
import com.gultendogan.shoppinglist.data.db.ShoppingDatabase
import com.gultendogan.shoppinglist.data.db.entities.ShoppingItem
import com.gultendogan.shoppinglist.data.repositories.ShoppingRepository
import com.gultendogan.shoppinglist.other.ShoppingItemAdapter
import com.gultendogan.shoppinglist.ui.shoppinglist.AddDialogListener
import com.gultendogan.shoppinglist.ui.shoppinglist.AddShoppingItemDialog
import com.gultendogan.shoppinglist.ui.shoppinglist.ShoppingViewModel
import com.gultendogan.shoppinglist.ui.shoppinglist.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(),viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}