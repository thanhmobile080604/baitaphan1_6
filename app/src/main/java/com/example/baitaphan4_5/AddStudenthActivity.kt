package com.example.baitaphan4_5

import MyHelper
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baitaphan4_5.databinding.ActivityAddStudenthBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityAddStudenthBinding
class AddStudenthActivity : AppCompatActivity() {
    //khai bao
    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddStudenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Khoi tao database va con tro rs chay trong db
        var helper = MyHelper(applicationContext)
        db = helper.readableDatabase
        rs = db.rawQuery("select * from quanly", null)


        binding.buttonadd.setOnClickListener {

            //cho dữ liệu vào cv và insert vào db
            var cv = ContentValues()
            cv.put("name", binding.editTextName.text.toString())
            cv.put("mssv", binding.editTextMSSV.text.toString())
            cv.put("sdt", binding.editTextSDT.text.toString())
            cv.put("email", binding.editTextEmail.text.toString())

            db.insert("quanly", null, cv)
            rs.requery()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}