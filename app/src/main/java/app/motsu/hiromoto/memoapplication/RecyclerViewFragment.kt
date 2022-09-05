package app.motsu.hiromoto.memoapplication

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.motsu.hiromoto.memoapplication.databinding.FragmentRecyclerViewBinding

class RecyclerViewFragment(itemArray: ArrayList<Item>) : Fragment() {

    private lateinit var binding: FragmentRecyclerViewBinding
    private val itemArray = itemArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerViewBinding.inflate(layoutInflater)

        val decoration = object : RecyclerView.ItemDecoration() {
//            override fun onDraw(
//                c: Canvas, parent: RecyclerView,
//                state: RecyclerView.State) {
//                // Canvasへ装飾を描画
//                // アイテムが描画される前に描画されるため、アイテムの下に表示
//            }
//
//            override fun onDrawOver(
//                c: Canvas, parent: RecyclerView,
//                state: RecyclerView.State) {
//                // Canvasへ装飾を描画
//                // アイテムが描画された後に描画されるため、アイテムの上に表示
//            }

            override fun getItemOffsets(
                outRect: Rect, view: View, parent: RecyclerView,
                state: RecyclerView.State) {
                // アイテムの上下左右へ空間を設ける
                // outRect.set(leftの空間, topの空間, rightの空間, bottomの空間)
                outRect.set(50, 10, 50, 10)
            }
        }


        binding.recyclerView.apply{
            adapter = ListAdapter(itemArray)
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            addItemDecoration(decoration)
        }

        binding.createButton.setOnClickListener {
            parentFragmentManager.beginTransaction().apply{
            replace(R.id.edit_fragment_container,
            EditFragment(itemArray),
        "EditFragment")
            }.commit()
//            parentFragmentManager.beginTransaction().remove(this).commit();
        }

        return binding.root
    }
}
