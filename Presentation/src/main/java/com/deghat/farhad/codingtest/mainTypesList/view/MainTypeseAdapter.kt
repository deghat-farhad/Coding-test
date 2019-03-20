package com.deghat.farhad.codingtest.mainTypesList.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.deghat.farhad.codingtest.R
import com.deghat.farhad.codingtest.model.MainTypesItem
import kotlin.reflect.KFunction1


class MainTypeseAdapter(
        private var wkda: ArrayList<MainTypesItem.MainType>,
        private var onItemClickListener: KFunction1<@ParameterName(name = "selectedManufacturer") String, Unit>
    ): RecyclerView.Adapter<MainTypeHolder>() {

    private enum class HolderType(val value: Int) {
        ODD(1),
        EVEN(2),
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTypeHolder {

        val view = if (viewType == HolderType.EVEN.value)
            LayoutInflater.from(parent.context).inflate(R.layout.even_holder, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.odd_holder, parent, false)

        return MainTypeHolder(view)
    }

    override fun getItemCount(): Int {
        return wkda.size

    }

    override fun onBindViewHolder(holder: MainTypeHolder, position: Int) {
        holder.txtViewManufacturer.text = wkda[position].value
        holder.manufacturerHolderRoot.setOnClickListener {
            onItemClickListener(wkda[position].value)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0)
            HolderType.EVEN.value
        else
            HolderType.ODD.value
    }


    fun setItems(mainTypes: ArrayList<MainTypesItem.MainType>) {
        wkda = mainTypes
        notifyDataSetChanged()
    }
}