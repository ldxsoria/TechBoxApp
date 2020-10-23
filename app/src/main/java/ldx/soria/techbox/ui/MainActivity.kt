package ldx.soria.techbox.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ldx.soria.techbox.MainAdapter
import ldx.soria.techbox.R
import ldx.soria.techbox.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    // viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    private val viewModel by lazy {ViewModelProvider(this).get(MainViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this)
        rv_items.layoutManager = LinearLayoutManager(this)
        rv_items.adapter = adapter
        observeData()


        imageAddItem.setOnClickListener {
            val intent1 = Intent(this, NewItem::class.java)
            startActivity(intent1)
        }

    }
    fun observeData(){
        viewModel.fetchItemData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

}