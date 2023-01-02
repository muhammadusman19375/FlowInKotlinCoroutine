package com.example.flowincoroutinekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flowincoroutinekotlin.data.NetworkResult
import com.example.flowincoroutinekotlin.databinding.ActivityMainBinding
import com.example.flowincoroutinekotlin.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.getData()

        mainViewModel.responseLiveData.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            when(it){
                is NetworkResult.Success -> {
                    binding.tvTextView.text = it.data.toString()
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this@MainActivity, it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })

    }
}