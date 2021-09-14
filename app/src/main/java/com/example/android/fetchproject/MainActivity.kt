package com.example.android.fetchproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fetchproject.databinding.ActivityMainBinding
import com.example.android.fetchproject.recyclerview.AccountAdapter


class MainActivity : AppCompatActivity() {
   private var adapter = AccountAdapter()
   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_main)

        val vm = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //attaches MainActivityViewModel with binding data viewModel
        binding.viewModel = vm
        binding.setLifecycleOwner(this)
        //attaches Account adapter to the recyclerView adapter
        binding.recylerView.adapter = adapter

        //informs user when the list has loaded
        vm.completeListOfUsers.observe(this, { it->
            Toast.makeText(this, "List has loaded!", Toast.LENGTH_SHORT).show()
        })

        vm.listToShowUsers.observe(this, {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

    }
}