package app.motsu.hiromoto.memoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.motsu.hiromoto.memoapplication.databinding.FragmentRecyclerViewBinding

class ListAdapter(private val itemList:ArrayList<Item>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val title:TextView = view.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
//        val itemView = FragmentRecyclerViewBinding.inflate(layoutInflater)
//        return ViewHolder(itemView.root)
        val item = layoutInflater.inflate(R.layout.memolist_item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = itemList.get(position).title
    }

    override fun getItemCount() = itemList.size
}