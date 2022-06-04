package com.ps215.capstoneITLS.ui.trafficdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ps215.capstoneITLS.databinding.ActivityTrafficDetailBinding

class TrafficDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrafficDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTrafficDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "${intent.getStringExtra(EXTRA_NAME)}"
        supportActionBar?.subtitle = "${intent.getStringExtra(EXTRA_ADDRESS)}"

    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ADDRESS = "extra_address"
    }
}