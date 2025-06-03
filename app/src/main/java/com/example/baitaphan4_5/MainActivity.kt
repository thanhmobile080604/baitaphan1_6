package com.example.baitaphan4_5

import MyHelper
import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baitaphan4_5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor
    lateinit var adapter: Adapter

    @SuppressLint("NotifyDataSetChanged")
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

        //Khoi tao database va con tro rs chay trong db
        var helper = MyHelper(applicationContext)
        db = helper.readableDatabase
        rs = db.rawQuery("select * from quanly", null)

        //tạo list model như binh thường và cho rs chạy cả db để lấy dữ liệu cho vào list
        val list = mutableListOf<StudentModel>()
        while (rs.moveToNext()) {
            val id = rs.getInt(rs.getColumnIndexOrThrow("_id"))
            val name = rs.getString(rs.getColumnIndexOrThrow("name"))
            val mssv = rs.getString(rs.getColumnIndexOrThrow("mssv"))
            val sdt = rs.getString(rs.getColumnIndexOrThrow("sdt"))
            val email = rs.getString(rs.getColumnIndexOrThrow("email"))
            list.add(StudentModel(id, name, mssv, sdt, email))
        }

        //Hien thi danh sach nhu binh thuong
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        binding.rvlist.layoutManager = layoutManager
        adapter = Adapter(list)
        adapter.notifyDataSetChanged()
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





}