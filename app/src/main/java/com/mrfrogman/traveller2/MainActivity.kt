package com.mrfrogman.traveller2

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mrfrogman.traveller2.component.MainScreen
import com.mrfrogman.traveller2.database.DatabaseHelper
import com.mrfrogman.traveller2.ui.theme.TravellerTheme

class MainActivity : ComponentActivity() {
    private lateinit var helper:DatabaseHelper
    private var dataList: MutableList<Map<String, Any>> = ArrayList()
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravellerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen()
                }
            }
        }
        helper = DatabaseHelper(applicationContext)
        val db: SQLiteDatabase = helper.readableDatabase
        val cursor = db.rawQuery("select * from plans",null)
        if (cursor.moveToFirst()) {
            do {
                val dataMap = mutableMapOf<String, Any>().apply {
                    put("_id", cursor.getInt(cursor.getColumnIndex("_id")))
                    put("title", cursor.getString(cursor.getColumnIndex("title")))
                    put("detail", cursor.getString(cursor.getColumnIndex("detail")))
                    put("member", cursor.getInt(cursor.getColumnIndex("member")))
                    put("amount", cursor.getInt(cursor.getColumnIndex("amount")))
                    put("date", cursor.getString(cursor.getColumnIndex("date")))
                }
                dataList.add(dataMap)
            } while (cursor.moveToNext())
        }
        cursor.close()
    }
}
@Composable
fun Screen(){
    MainScreen()
}

@Preview(showBackground = true, widthDp = 340)
@Composable
fun MainPreview() {
    TravellerTheme {
        Screen()
    }
}