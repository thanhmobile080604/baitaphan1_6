import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.widget.SearchView

import android.database.sqlite.SQLiteOpenHelper

//tao bàng và data
class MyHelper(context: Context) : SQLiteOpenHelper(context, "quanly", null, 3) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE quanly (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, mssv TEXT, sdt TEXT, email TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS quanly")
        onCreate(db)
    }
}
