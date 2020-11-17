package com.example.openapiex04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class Adapter : RecyclerView.Adapter<Adapter.Holder>() {
    var list = mutableListOf<XXX>()

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){

        init {
            itemView.setOnClickListener{

            }
        }

        fun setItem(item: XXX) {

            itemView.textName.text = item.stationName
            itemView.textLoc.text = "${item.dmX}, ${item.dmY}"
            itemView.textAddr.text = item.addr
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view  = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.get(position)

        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

data class XX (val stationName : String, val dmX : Double, val dmY : Double)