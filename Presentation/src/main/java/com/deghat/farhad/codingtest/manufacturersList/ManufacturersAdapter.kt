package com.deghat.farhad.codingtest.manufacturersList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.deghat.farhad.codingtest.R
import com.deghat.farhad.codingtest.model.ManufacturersItem
import kotlin.reflect.KFunction2


class ManufacturersAdapter(
        private var wkda: ArrayList<ManufacturersItem.Manufacturer>,
        private var onItemClickListener: KFunction2<@ParameterName(name = "selectedManufacturerId") String, @ParameterName(name = "selectedManufacturerName") String, Unit>,
        private var onRetryClickListener: () -> Unit
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class HolderType(val value: Int) {
        ODD(1),
        EVEN(2),
        PROGRESS(3),
        ERROR(4)
    }

    private var isProgressBarNeeded = false
    private var isError = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = when (viewType) {
            HolderType.ERROR.value -> LayoutInflater.from(parent.context).inflate(R.layout.connection_error_holder, parent, false)
            HolderType.PROGRESS.value -> LayoutInflater.from(parent.context).inflate(R.layout.progress_bar_holder, parent, false)
            HolderType.EVEN.value -> LayoutInflater.from(parent.context).inflate(R.layout.even_holder, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.odd_holder, parent, false)
        }

        return when (viewType) {
            HolderType.ERROR.value -> ConnectionErrorHolder(view)
            HolderType.PROGRESS.value -> object : RecyclerView.ViewHolder(view) {}
            else -> ManufacturerHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return wkda.size + if (isProgressBarNeeded || isError) 1 else 0

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            HolderType.ODD.value, HolderType.EVEN.value -> {
                val manufacturerHolder = holder as ManufacturerHolder
                manufacturerHolder.txtViewManufacturer.text = wkda[position].value
                manufacturerHolder.manufacturerHolderRoot.setOnClickListener {
                    onItemClickListener(wkda[position].key, wkda[position].value)
                }
            }
            HolderType.ERROR.value ->
                (holder as ConnectionErrorHolder).ConnectionErrorHolderRoot.setOnClickListener {
                    onRetryClickListener()
                }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {

            position == wkda.size ->
                if (isError)
                    HolderType.ERROR.value
                else
                    HolderType.PROGRESS.value

            position % 2 == 0 -> HolderType.EVEN.value

            else -> HolderType.ODD.value
        }
    }

    fun hideProgress() {
        isProgressBarNeeded = false
        notifyItemRemoved(wkda.size)
    }

    fun showProgress() {
        isProgressBarNeeded = true
        notifyItemInserted(wkda.size)
    }

    fun showError() {
        isError = true
        notifyItemChanged(wkda.size)
    }

    fun hideError() {
        isError = false
        notifyItemChanged(wkda.size)
    }
}