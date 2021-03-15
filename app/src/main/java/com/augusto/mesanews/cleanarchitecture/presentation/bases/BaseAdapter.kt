package com.augusto.mesanews.cleanarchitecture.presentation.bases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : RecyclerView.ViewHolder?, U>(private val view: Int): RecyclerView.Adapter<T>() {

    protected var list: List<U> = mutableListOf()

    fun update(newList: List<U>) {
        list = newList
        notifyDataSetChanged()
    }

    fun updateItem(item: U) {
        val index = list.indexOf(item)
        if (index < 0) {
            return
        }

        val newList = list.toMutableList()
        newList.add(index, item)
        list = newList

        notifyItemChanged(index)
    }

    fun insertItens(itens: List<U>) {
        val initialIndex = itens.count()
        val newList = list.toMutableList()
        newList.addAll(itens)
        list = newList

        notifyItemRangeChanged(initialIndex, itens.count())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val v = LayoutInflater.from(parent.context)
                .inflate(view, parent, false)

        return createHolder(v)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        onBind(holder, list[position])
    }

    abstract fun onBind(holder: T, item: U)

    abstract fun createHolder(view: View): T

    override fun getItemCount(): Int = list.size
}