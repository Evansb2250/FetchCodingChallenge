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
import com.example.android.fetchproject.repository.Repository

class MainActivity : AppCompatActivity() {
    lateinit var adapter:AccountAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_main)

        val vm = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewModel = vm
        binding.setLifecycleOwner(this)

        vm.completeListOfUsers.observe(this, { it->
            for (i in it){
                print("${i}")
            }

        })

        vm.listToShowUsers.observe(this, {
            adapter= AccountAdapter(it)
            binding.recylerView.adapter = adapter
        })

    }
}