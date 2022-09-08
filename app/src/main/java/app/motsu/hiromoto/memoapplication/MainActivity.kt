package app.motsu.hiromoto.memoapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
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
            savedStrData.split(';').forEach{
                if(!it.isNullOrBlank()){
                    val tmpArray = it.split(':')
                    items.add(Item(tmpArray[0],tmpArray[1]))
                }
            }
            Log.d(TAG, "list = $items")
        }else{
            Log.d(TAG, "Saved data is nothing")
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
            saveData += "${it};"
        }
        Log.d(TAG, "onStop: \n$saveData")
        getSharedPreferences("ListData", Context.MODE_PRIVATE).edit().apply{
            putString("JSON_DATA", saveData).apply()
        } ?: throw Exception("Can't get SharedPreferences")
        super.onStop()
    }

    override fun onBackPressed() {

        if(supportFragmentManager.findFragmentById(R.id.edit_fragment_container)!!.tag != "RecyclerFragment"){
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.edit_fragment_container,
                    RecyclerViewFragment(this@MainActivity),
                    "RecyclerFragment")
            }.commit()
        }else{
            super.onBackPressed()
        }
    }
}

class Item{

    var title = ""
    var memo = ""

    constructor(title: String) {
        this.title = title
    }
    constructor(title:String, memo:String){
        this.title = title
        this.memo = memo
    }

    override fun toString(): String {
        return "$title:$memo"
    }
}