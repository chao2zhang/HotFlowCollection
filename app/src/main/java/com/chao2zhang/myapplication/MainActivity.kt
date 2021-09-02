package com.chao2zhang.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.chao2zhang.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.plusOne.setOnClickListener {
            viewModel.increment()
        }

        lifecycleScope.launchWhenResumed {
            viewModel.transformedFlow.collect {
                binding.transformedFlow.text = it.toString()
            }
            viewModel.sourceFlow.collect {
                binding.sourceFlow.text = it.toString()
            }
        }
    }
}