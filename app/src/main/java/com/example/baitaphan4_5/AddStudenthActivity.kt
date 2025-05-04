package com.example.baitaphan4_5

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baitaphan4_5.databinding.ActivityAddStudenthBinding

private lateinit var binding: ActivityAddStudenthBinding
class AddStudenthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddStudenthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonadd.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val mssv = binding.editTextMSSV.text.toString()
            val sdt = binding.editTextSDT.text.toString()
            val email = binding.editTextEmail.text.toString()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("mssv", mssv)
            intent.putExtra("sdt", sdt)
            intent.putExtra("email", email)
            intent.putExtra("add", true)
            startActivity(intent)
        }
    }
}