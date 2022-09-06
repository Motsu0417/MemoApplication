package app.motsu.hiromoto.memoapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.motsu.hiromoto.memoapplication.databinding.FragmentEditBinding

class EditFragment() : Fragment() {

    private lateinit var binding:FragmentEditBinding
    private val itemArray = MainActivity.itemArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater)

        binding.doneButton.setOnClickListener{
            val titleText = binding.memoEditText.text.toString()
            if(titleText.isNullOrBlank()){
                Toast.makeText(this.context, "正しく入力してください", Toast.LENGTH_SHORT).show()

            }else{
                itemArray.add(Item().apply { title = titleText})
                Toast.makeText(this.context, "保存しました", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction().apply{
                    replace(R.id.edit_fragment_container,
                    RecyclerViewFragment(),
                "RecyclerFragment")
                }.commit()
            }
        }

        return binding.root
    }
}
