package com.akshai.tutorials.wisdomleaftest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akshai.tutorials.wisdomleaftest.databinding.FragmentViewSheetBinding
import com.akshai.tutorials.wisdomleaftest.domain.ListDomainModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * Created by ATM on 20/October/2022
 */

class ViewBottomSheet(
    val modelData: ListDomainModel,
    val onLinkClicked: (url : String) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentViewSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            model = modelData
            visitPage.setOnClickListener {
                dismiss()
                onLinkClicked.invoke(modelData.url.toString())
            }
        }
    }
}