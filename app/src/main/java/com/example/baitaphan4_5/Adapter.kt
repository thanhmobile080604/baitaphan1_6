package com.example.baitaphan4_5

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.baitaphan4_5.R
import com.example.baitaphan4_5.StudentModel

class Adapter(private val list : MutableList<StudentModel>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.textView1)
        val mssv = itemView.findViewById<TextView>(R.id.textView2)
        val sdt = itemView.findViewById<TextView>(R.id.textView3)
        val email = itemView.findViewById<TextView>(R.id.textView4)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student = list[position]
        holder.name.text = student.name
        holder.mssv.text = student.mssv
        holder.sdt.text = student.sdt
        holder.email.text = student.email

        holder.itemView.setOnClickListener {
            val popup = PopupMenu(holder.itemView.context, holder.itemView)

            popup.menu.add(Menu.NONE, 1, 1, "Cập nhật")
            popup.menu.add(Menu.NONE, 2, 2, "Xóa")
            popup.menu.add(Menu.NONE, 3, 3, "Gọi điện")
            popup.menu.add(Menu.NONE, 4, 4, "Gửi email")

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> {
                         val intent = Intent(holder.itemView.context, UpdateStudentActivity::class.java)
                        intent.putExtra("student_id", student.id)
                         holder.itemView.context.startActivity(intent)
                         true
                    }

                    2 -> {
                        AlertDialog.Builder(holder.itemView.context)
                            .setTitle("Xóa sinh viên")
                            .setMessage("Bạn có chắc chắn muốn xóa ${student.name}?")
                            .setPositiveButton("Có") { _, _ ->
                                list.remove(student)
                                notifyDataSetChanged()
                            }
                            .setNegativeButton("Không", null)
                            .show()

                    }

                    3 -> {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${student.sdt}"))
                        holder.itemView.context.startActivity(intent)
                    }

                    4 -> {
                        val intent =
                            Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${student.email}"))
                        holder.itemView.context.startActivity(intent)
                    }
                }
                true
            }
            popup.show()
        }







    }


}