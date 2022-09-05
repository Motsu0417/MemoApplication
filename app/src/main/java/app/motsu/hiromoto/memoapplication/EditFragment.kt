package app.motsu.hiromoto.memoapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.motsu.hiromoto.memoapplication.databinding.FragmentEditBinding

class EditFragment(itemArray: ArrayList<Item>) : Fragment() {

    private lateinit var binding:FragmentEditBinding
    private val itemArray = itemArray

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater)

        binding.doneButton.setOnClickListener{
//            itemArray.add()

        }

        return binding.root
    }
}
