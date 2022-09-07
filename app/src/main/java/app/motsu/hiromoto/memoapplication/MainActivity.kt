package app.motsu.hiromoto.memoapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.motsu.hiromoto.memoapplication.databinding.ActivityMainBinding

const val TAG = "debug"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {setContentView(this.root)}

        val savedStrData = getSharedPreferences("ListData", Context.MODE_PRIVATE).getString("JSON_DATA", null)
        if(!savedStrData.isNullOrEmpty()){
            Log.d(TAG, "data = $savedStrData")
            items.clear()
            savedStrData.split(',').forEach{
                if(!it.isNullOrBlank()){
                    items.add(Item().apply{title = it})
                }
            }
            Log.d(TAG, "list = $items")
        }
//        itemArray.addAll((Array<Item>(10){Item().apply { title = "item $it" }}))


        supportFragmentManager.beginTransaction().apply{
            replace(R.id.edit_fragment_container,
            RecyclerViewFragment(this@MainActivity),
        "RecyclerFragment")
        }.commit()
    }

    companion object{
        val items:ArrayList<Item> = ArrayList()
    }

    override fun onStop() {
        var saveData = ""
        items.forEach{
            saveData += "$it,"
        }
        Log.d(TAG, "onStop: \n$saveData")
        getSharedPreferences("ListData", Context.MODE_PRIVATE).edit().apply{
            putString("JSON_DATA", saveData).apply()
        } ?: throw Exception("Can't get SharedPreferences")
        super.onStop()
    }
}

class Item{
    var title = ""
    fun Item(title: String) {
        this.title = title
    }

    override fun toString(): String {
        return title
    }
}