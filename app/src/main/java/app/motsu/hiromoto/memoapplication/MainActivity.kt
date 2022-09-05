package app.motsu.hiromoto.memoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.motsu.hiromoto.memoapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val ItemArray:ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {setContentView(this.root)}

        binding.createButton.setOnClickListener{

        }

    }
}

class Item{
    var title = ""
}