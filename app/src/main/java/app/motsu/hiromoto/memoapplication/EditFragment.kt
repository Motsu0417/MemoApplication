package app.motsu.hiromoto.memoapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.motsu.hiromoto.memoapplication.databinding.FragmentEditBinding

class EditFragment(
    private var rootActivity: AppCompatActivity,
    private val position: Int?,
) : Fragment() {

    private lateinit var binding:FragmentEditBinding
    private val items = MainActivity.items

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater)

        if (position != null) {
            binding.memoEditText.setText(items[position].title)
            binding.doneButton.visibility = INVISIBLE
        }else{
            binding.deleteButton.visibility = INVISIBLE
            binding.saveButton.visibility = INVISIBLE
        }

        binding.deleteButton.setOnClickListener{
            items.removeAt(position!!)
            Toast.makeText(this.context, "削除しました", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().apply{
                replace(R.id.edit_fragment_container,
                    RecyclerViewFragment(rootActivity),
                    "RecyclerFragment")
            }.commit()
        }

        binding.saveButton.setOnClickListener {
            val titleText = binding.memoEditText.text.toString()
            if(titleText.isNullOrBlank()){
                Toast.makeText(this.context, "正しく入力してください", Toast.LENGTH_SHORT).show()
            }else {
                items[position!!] = Item().apply { title = titleText }
                Toast.makeText(this.context, "保存しました", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction().apply {
                    replace(
                        R.id.edit_fragment_container,
                        RecyclerViewFragment(rootActivity),
                        "RecyclerFragment"
                    )
                }.commit()
            }
        }

        binding.doneButton.setOnClickListener{
            val titleText = binding.memoEditText.text.toString()
            if(titleText.isNullOrBlank()){
                Toast.makeText(this.context, "正しく入力してください", Toast.LENGTH_SHORT).show()

            }else{
                items.add(Item().apply { title = titleText})
                Toast.makeText(this.context, "保存しました", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction().apply{
                    replace(R.id.edit_fragment_container,
                    RecyclerViewFragment(rootActivity),
                "RecyclerFragment")
                }.commit()
            }
        }

        return binding.root
    }
}
