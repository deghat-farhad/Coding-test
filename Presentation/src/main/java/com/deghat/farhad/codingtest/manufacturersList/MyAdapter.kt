package com.deghat.farhad.codingtest.manufacturersList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.deghat.farhad.codingtest.R
import com.deghat.farhad.codingtest.model.ManufacturersItem

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    lateinit var wkda: ArrayList<ManufacturersItem.Manufacturer>

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val item: View) : RecyclerView.ViewHolder(item){
        val txtView: TextView = item.findViewById(R.id.my_text_view)
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        // create a new view
        val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(item)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //val itemKey = wkda.keys.toList()[position]
        holder.txtView.text = wkda[position].value
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = wkda.size

    fun setItems(manufacturers: ArrayList<ManufacturersItem.Manufacturer>){
        wkda = manufacturers
        notifyDataSetChanged()
    }


}