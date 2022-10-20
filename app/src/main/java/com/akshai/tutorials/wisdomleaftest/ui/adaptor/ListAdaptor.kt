package com.akshai.tutorials.wisdomleaftest.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.akshai.tutorials.wisdomleaftest.databinding.ListItemLayoutBinding
import com.akshai.tutorials.wisdomleaftest.domain.ListDomainModel
import com.akshai.tutorials.wisdomleaftest.ui.clickEvent.ClickEventListener
import com.akshai.tutorials.wisdomleaftest.utils.ListDiffCallback
import javax.inject.Inject


/**
 * Created by ATM on 20/October/2022
 */

class ListAdaptor @Inject constructor() : RecyclerView.Adapter<ListAdaptor.ListViewHolder>() {

    private lateinit var mListener : ClickEventListener<ListDomainModel>

    inner class ListViewHolder(val binding: ListItemLayoutBinding)  : ViewHolder(binding.root){

        fun setData(listDomainModel: ListDomainModel, position: Int) {
            binding.model = listDomainModel
            binding.itemView.setOnClickListener { mListener.onItemClicked(listDomainModel) }
        }

    }

    private var list = arrayListOf<ListDomainModel>()

    fun setClickListener(listener: ClickEventListener<ListDomainModel>) {
        mListener = listener
    }


    fun setAdaptor(newList : ArrayList<ListDomainModel>){
        val diffCallback = ListDiffCallback(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.setData(list[position],position)
    }

    override fun getItemCount(): Int = list.size
}