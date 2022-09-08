package app.motsu.hiromoto.memoapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import app.motsu.hiromoto.memoapplication.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*

const val TAG = "debug"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {setContentView(this.root)}

        // jsonの読み込み
        val assetManager = resources.assets
        try {
//            val inputStream = assetManager.open("ListData.json")
            val filepath = filesDir.path + "/ListData.json"
            val file = File(filepath)
            // ファイルが存在していれば読み込みを行う
            if(file.exists()){
                val bufferedReader = BufferedReader(FileReader(file))
                val str = bufferedReader.readText()
                // JsonArrayに格納する
                val jsonArray = JSONArray(str)
                for (i in 0 until jsonArray.length()) {
                    jsonArray.getJSONObject(i).let {
                        items.add(Item(it.getString("title"), it.getString("memo")))
                    }
                }
            }else{
            Log.d(TAG, "Saved data is nothing")
        }

        }catch(e:Exception){
            Log.e(TAG, "onCreate: error", e)
        }


        // SharedPrefrencesを利用したデータの読み込み
//        val savedStrData = getSharedPreferences("ListData", Context.MODE_PRIVATE).getString("JSON_DATA", null)
//        if(!savedStrData.isNullOrEmpty()){
//            Log.d(TAG, "data = $savedStrData")
//            items.clear()
//            savedStrData.split(';').forEach{
//                if(!it.isNullOrBlank()){
//                    val tmpArray = it.split(':')
//                    items.add(Item(tmpArray[0],tmpArray[1]))
//                }
//            }
//            Log.d(TAG, "list = $items")
//        }else{
//            Log.d(TAG, "Saved data is nothing")
//        }

        // リスト用フラグメントへの遷移
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

        // jsonの書き出し
        try{
            val filepath = filesDir.path + "/ListData.json"
            val file = File(filepath)
            val fileWriter = FileWriter(file, false)
            val bufferdWriter = BufferedWriter(fileWriter)

            val jsonArray = JSONArray()
            items.forEach{
                val jsonObject = JSONObject()
                jsonObject.put("title", it.title)
                jsonObject.put("memo", it.memo)
                jsonArray.put(jsonObject)
            }
            Log.d(TAG, "onStop: $jsonArray")
            bufferdWriter.write(jsonArray.toString())
            bufferdWriter.close()
        }catch(e:Exception){
            Log.e(TAG, "onStop: ", e)
        }



        // SharedPreferencesを利用したデータ保存
//        var saveData = ""
//        items.forEach{
//            saveData += "${it};"
//        }
//        Log.d(TAG, "onStop: \n$saveData")
//        getSharedPreferences("ListData", Context.MODE_PRIVATE).edit().apply{
//            putString("JSON_DATA", saveData).apply()
//        } ?: throw Exception("Can't get SharedPreferences")

        super.onStop()
    }

    override fun onBackPressed() {
        // 「戻る」ボタンを推した際にRecyclerFragmentに戻るよう設定
        // 元々RecyclerFragmentならそのまま終了
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