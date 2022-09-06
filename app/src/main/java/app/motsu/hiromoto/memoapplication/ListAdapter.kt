package app.motsu.hiromoto.memoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.motsu.hiromoto.memoapplication.databinding.FragmentRecyclerViewBinding

class ListAdapter(private val itemList:ArrayList<Item>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val title:TextView = view.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.memolist_item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = itemList.get(position).title
        holder.view.setOnClickListener {
//            Toast.makeText(holder.view.context, holder.title.text.toString(),Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount() = itemList.size
}