package com.ps215.capstoneITLS.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ps215.capstoneITLS.data.model.TrafficList
import com.ps215.capstoneITLS.databinding.FragmentDashboardBinding
import com.ps215.capstoneITLS.ui.ViewModelFactory
import com.ps215.capstoneITLS.ui.trafficdetail.TrafficDetailActivity

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
        dashboardViewModel.setTraffic()
        getTraffic()
        showLoading(true)
    }

    private fun getTraffic() {
        val adapter = ListTrafficAdapter()
        binding.trafficRv.adapter = adapter
        dashboardViewModel.getTraffic().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            showLoading(false)
        }
        adapter.setOnItemClickCallback(object : ListTrafficAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TrafficList) {
                showSelectedItem(data)
            }
        })
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.pBar.visibility = View.VISIBLE
        }else{
            binding.pBar.visibility = View.INVISIBLE
        }
    }

    private fun showSelectedItem(data: TrafficList) {
        val intent = Intent (activity, TrafficDetailActivity::class.java)
        intent.putExtra(TrafficDetailActivity.EXTRA_NAME, data.name)
        intent.putExtra(TrafficDetailActivity.EXTRA_ID, data._id)
        intent.putExtra(TrafficDetailActivity.EXTRA_ADDRESS, data.address)
        activity?.startActivity(intent)
        Toast.makeText(activity, data.name + " Selected" , Toast.LENGTH_SHORT).show()
    }
}