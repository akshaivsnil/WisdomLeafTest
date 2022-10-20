package com.akshai.tutorials.wisdomleaftest.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.akshai.tutorials.wisdomleaftest.databinding.FragmentMainBinding
import com.akshai.tutorials.wisdomleaftest.domain.ListDomainModel
import com.akshai.tutorials.wisdomleaftest.repository.DataHandler
import com.akshai.tutorials.wisdomleaftest.ui.adaptor.ListAdaptor
import com.akshai.tutorials.wisdomleaftest.utils.showToast
import com.akshai.tutorials.wisdomleaftest.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() , SwipeRefreshLayout.OnRefreshListener  {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel : MainViewModel by viewModels()

    @Inject
    lateinit var listAdaptor : ListAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    companion object {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            swiper.setOnRefreshListener(this@MainFragment)
        }


        mainViewModel.responseLiveData.observe(viewLifecycleOwner) {
            when(it){
                is DataHandler.ERROR -> it.message?.let { it1 -> requireContext().showToast(it1) }
                is DataHandler.LOADING -> binding.swiper.isRefreshing = true
                is DataHandler.SUCCESS ->  {
                    binding.swiper.isRefreshing = false
                    mainViewModel.response = it.data
                    binding.listRecycler.adapter = listAdaptor
                    listAdaptor.setAdaptor(mainViewModel.response as ArrayList<ListDomainModel>)
                }
            }
        }


    }

    override fun onRefresh() {
        mainViewModel.response?.let {
            listAdaptor.setAdaptor(mainViewModel.response!!.shuffled() as ArrayList<ListDomainModel>)
            binding.swiper.isRefreshing = false
        }
    }
}

