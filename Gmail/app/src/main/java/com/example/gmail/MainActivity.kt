package com.example.gmail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Create sample email data
        val emailList = listOf(
            Email("Kha Minh Bao", "20210098", "Reminder: Submit assignment before 23:59PM 30 Oct, 2024", "16:08 PM"),
            Email("Somebody", "Test","I did it!", "13:36 PM"),
            Email("John Doe", "Meeting Reminder", "Don't forget the meeting at 3 PM.", "12:34 PM"),
            Email("Amazon", "Order Confirmation", "Your order has been shipped.", "11:22 AM"),
            Email("Netflix", "New Releases", "Check out what's new on Netflix.", "10:45 AM"),
            Email("Jane Smith", "Lunch Plan", "Are we still on for lunch tomorrow?", "9:30 AM"),
            Email("Bank", "Account Statement", "Your monthly statement is ready to view.", "Yesterday")
        )

        val adapter = EmailAdapter(emailList)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter

        // Floating Action Button click action
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this, "Compose Email", Toast.LENGTH_SHORT).show()
        }
    }
}
