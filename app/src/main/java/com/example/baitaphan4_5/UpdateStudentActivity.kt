package com.example.baitaphan4_5

import MyHelper
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baitaphan4_5.databinding.ActivityUpdateStudentBinding

private lateinit var binding: ActivityUpdateStudentBinding
class UpdateStudentActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Khoi tao database va con tro rs chay trong db
        var helper = MyHelper(applicationContext)
        db = helper.readableDatabase

        //bắt id từ adapter gửi đi
        val id = intent.getIntExtra("student_id", -1)
        //con trỏ chạy đi tìm id tương ứng
        rs = db.rawQuery("SELECT * FROM quanly WHERE _id = $id", null)

        binding.buttonupdate.setOnClickListener {

            //cho dữ liệu vào cv và upadate theo id vào db
            var cv = ContentValues()
            cv.put("name", binding.editTextName.text.toString())
            cv.put("mssv", binding.editTextMSSV.text.toString())
            cv.put("sdt", binding.editTextSDT.text.toString())
            cv.put("email", binding.editTextEmail.text.toString())
            db.update("quanly", cv, "_id=?", arrayOf(id.toString()))
            rs.requery()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}