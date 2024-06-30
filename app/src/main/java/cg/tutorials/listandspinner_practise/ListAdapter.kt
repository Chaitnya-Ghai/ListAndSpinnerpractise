package cg.tutorials.listandspinner_practise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(var list:ArrayList<Items>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.layout_list_view_row_items, parent, false)
        val tvQuantity = view.findViewById<TextView>(R.id.tvQuantity)
        tvQuantity.text=(list[position].quantity.toString())
        val tvName = view.findViewById<TextView>(R.id.tvName)
        tvName.text=(list[position].name)
        return view
    }
}