package app.motsu.hiromoto.memoapplication

import android.os.Build
import android.os.Bundle
import android.system.Os.accept
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import app.motsu.hiromoto.memoapplication.databinding.FragmentDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DetailFragment(private val rootActivity: AppCompatActivity, val position:Int) : Fragment() {

    private val items = MainActivity.items
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // これを宣言しないとMenuが反応しないとのこと
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    val dialogBuilder = MaterialAlertDialogBuilder(rootActivity)
        .setMessage("${items[position].title}を削除しますか？")
        .setNegativeButton("削除しない") { dialog, which ->
            // Respond to negative button press
        }
        .setPositiveButton("削除する") { dialog, which ->
            items.removeAt(position)
            Log.d(TAG, "onOptionsItemSelected: delete Item")
            parentFragmentManager.beginTransaction().apply {
                replace(
                    R.id.edit_fragment_container,
                    RecyclerViewFragment(rootActivity),
                    "RecyclerFragment"
                )
            }.commit()
            Toast.makeText(context, "削除しました", Toast.LENGTH_SHORT).show()
        }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // bindingを定義しそれぞれTextViewにメモの値を代入
        binding = FragmentDetailBinding.inflate(layoutInflater)
        binding.titleTextView.text = items[position].title
        binding.memoTextView.text = items[position].memo

        // toolbarのメニュークリックに機能を付与
        binding.toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.action_delete ->{
                    dialogBuilder.show()
                    return@setOnMenuItemClickListener false
                }
            }
            return@setOnMenuItemClickListener true
        }

        // FloatingActionButton押下時はEditFragmentに遷移
        binding.detailEditButton.setOnClickListener{
            parentFragmentManager.beginTransaction().apply{
                replace(R.id.edit_fragment_container,
                    EditFragment(rootActivity, position),
                    "EditFragment")
            }.commit()
        }
        // 最後にLayoutを返す
        return binding.root
    }
}