package app.motsu.hiromoto.memoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val items:ArrayList<Item>, private val rootActivity: AppCompatActivity): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val titleTextView:TextView = view.findViewById(R.id.item_title)
        val memoTextView:TextView = view.findViewById(R.id.item_memo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.memolist_item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = items[position].title
        holder.memoTextView.text = items[position].memo

        // リストのアイテムクリック時に詳細画面への遷移を行う機能の付与
        holder.view.setOnClickListener {
            rootActivity.supportFragmentManager.beginTransaction().apply{
                replace(R.id.edit_fragment_container,
                    DetailFragment(rootActivity, position),
                    "DetailFragment")
            }.commit()
        }
    }

    override fun getItemCount() = items.size
}