package com.example.android.fetchproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fetchproject.recyclerview.AccountAdapter
import com.example.android.fetchproject.repository.Repository

class MainActivity : AppCompatActivity() {
    lateinit var adapter:AccountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vm = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        vm.listToShowUsers.observe(this, {
            adapter = AccountAdapter(it)
            recyclerView.adapter = adapter
        })


    }
}