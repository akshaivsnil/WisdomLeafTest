package com.akshai.tutorials.wisdomleaftest.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.akshai.tutorials.wisdomleaftest.databinding.FragmentMainBinding
import com.akshai.tutorials.wisdomleaftest.domain.ListDomainModel
import com.akshai.tutorials.wisdomleaftest.repository.DataHandler
import com.akshai.tutorials.wisdomleaftest.ui.adaptor.ListAdaptor
import com.akshai.tutorials.wisdomleaftest.ui.clickEvent.ClickEventListener
import com.akshai.tutorials.wisdomleaftest.utils.showToast
import com.akshai.tutorials.wisdomleaftest.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    ClickEventListener<ListDomainModel> {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var listAdaptor: ListAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            swiper.setOnRefreshListener(this@MainFragment)
            listRecycler.adapter = listAdaptor
            listAdaptor.setClickListener(this@MainFragment)
        }


        mainViewModel.responseLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataHandler.ERROR -> it.message?.let { it1 -> requireContext().showToast(it1) }
                is DataHandler.LOADING -> binding.swiper.isRefreshing = true
                is DataHandler.SUCCESS -> {
                    binding.swiper.isRefreshing = false
                    mainViewModel.response = it.data
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


    override fun onItemClicked(model: ListDomainModel) {
        val bottomSheetFragment = ViewBottomSheet(model,
            onLinkClicked = { url ->
                actionToOpenBrowser(url)
            })
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    /**
     * Function to open any browser
     * @param url is the link to the page
     */
    private fun actionToOpenBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}

