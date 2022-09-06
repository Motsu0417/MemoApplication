package app.motsu.hiromoto.memoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.motsu.hiromoto.memoapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {setContentView(this.root)}

        itemArray.addAll((Array<Item>(10){Item().apply { title = "item $it" }}))


        supportFragmentManager.beginTransaction().apply{
            replace(R.id.edit_fragment_container,
            RecyclerViewFragment(),
        "RecyclerFragment")
        }.commit()
    }

    companion object{
        val itemArray:ArrayList<Item> = ArrayList()
    }
}

public class Item{
    var title = ""
    fun Item(title: String) {
        this.title = title
    }
}