package com.ps215.capstoneITLS.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ps215.capstoneITLS.database.Traffic
import com.ps215.capstoneITLS.databinding.FragmentDashboardBinding
import com.ps215.capstoneITLS.ui.ViewModelFactory

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.trafficRv.layoutManager = LinearLayoutManager(activity)

        setupViewModel()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(requireContext())
        dashboardViewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]
        dashboardViewModel.getAllTraffic()
        getTraffic()
    }

    private fun getTraffic() {
        val adapter = ListTrafficAdapter()
        binding.trafficRv.adapter = adapter
        dashboardViewModel.getAllTraffic().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        adapter.setOnItemClickCallback(object : ListTrafficAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Traffic) {
                showSelectedItem(data)
            }
        })
    }

    private fun showSelectedItem(data: Traffic) {
//        val intent = Intent (this@MainActivity, DetailUserActivity::class.java)
//        intent.putExtra(DetailUserActivity.EXTRA_AVATAR, user.avatarUrl)
//        intent.putExtra(DetailUserActivity.EXTRA_ID, user.id)
//        intent.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
//        startActivity(intent)
        Toast.makeText(activity, data.name + " Selected" , Toast.LENGTH_SHORT).show()
    }
}