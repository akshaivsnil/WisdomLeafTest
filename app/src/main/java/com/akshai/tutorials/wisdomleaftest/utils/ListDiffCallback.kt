package com.akshai.tutorials.wisdomleaftest.utils

import androidx.recyclerview.widget.DiffUtil
import com.akshai.tutorials.wisdomleaftest.domain.ListDomainModel


/**
 * Created by ATM on 20/October/2022
 */

class ListDiffCallback(
    private val oldList: List<ListDomainModel>,
    private val newList: List<ListDomainModel>
    ) : DiffUtil.Callback()  {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id === newList.get(newItemPosition).id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_, value, name) = oldList[oldPosition]
        val (_, value1, name1) = newList[newPosition]
        return name == name1 && value == value1
    }
}