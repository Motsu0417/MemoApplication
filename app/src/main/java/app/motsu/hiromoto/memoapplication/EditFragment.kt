package app.motsu.hiromoto.memoapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import app.motsu.hiromoto.memoapplication.databinding.FragmentEditBinding

class EditFragment(
    private var rootActivity: AppCompatActivity,
    private val position: Int?,
) : Fragment() {

    private lateinit var binding:FragmentEditBinding
    private val items = MainActivity.items

    val doneClickFromEdit = View.OnClickListener{
        getItemFromInput().let{
            if (it != null) {
                items[position!!] = (it)
            }
        }
    }

    val doneClickFromCreate = View.OnClickListener{
        getItemFromInput().let{
            if (it != null) {
                items.add(it)
            }
        }
    }

    private fun getItemFromInput():Item?{
        val titleText = binding.titleEditText.text.toString()
        val memoText = binding.memoEditText.text.toString()
        if(titleText.isNullOrBlank()){
            Toast.makeText(this.context, "正しく入力してください", Toast.LENGTH_SHORT).show()
            return null
        }else{
            Toast.makeText(this.context, "保存しました", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().apply{
                replace(R.id.edit_fragment_container,
                    RecyclerViewFragment(rootActivity),
                    "RecyclerFragment")
            }.commit()
        }
        return Item(titleText, memoText)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater)

        if (position != null) {
            items[position].let {
                binding.titleEditText.setText(it.title)
                binding.memoEditText.setText(it.memo)
            }
            binding.doneButton.setOnClickListener(doneClickFromEdit)
        }else{
            binding.doneButton.setOnClickListener(doneClickFromCreate)
        }
        return binding.root
    }


}
