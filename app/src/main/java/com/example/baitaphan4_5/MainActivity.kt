package com.example.baitaphan4_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baitaphan4_5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val list = mutableListOf<StudentModel>()
    lateinit var adapter : Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.rvlist.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(list)
        binding.rvlist.adapter = adapter





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            val intent = Intent(this, AddStudenthActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        val name = intent.getStringExtra("name")
        val mssv = intent.getStringExtra("mssv")
        val sdt = intent.getStringExtra("sdt")
        val email = intent.getStringExtra("email")
        val update = intent.getBooleanExtra("update", false)
        val add = intent.getBooleanExtra("add", false)

        if (update) {
            val position = intent.getIntExtra("position", -1)
            if (position != -1) {
                list[position] = StudentModel(name, mssv, sdt, email)
                adapter.notifyItemChanged(position)
            }
        }
        else if(add){
            if (name != null && mssv != null && sdt != null && email != null) {
                list.add(StudentModel(name, mssv, sdt, email))
                adapter.notifyDataSetChanged()
            }
        }
    }

}