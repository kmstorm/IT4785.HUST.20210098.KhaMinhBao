package com.example.findlnlistapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findlnlistapp.R
import com.example.findlnlistapp.StudentAdapter
import com.example.findlnlistapp.Student

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var originalStudentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchEditText = findViewById<EditText>(R.id.etSearch)
        val studentRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        originalStudentList = generateMockStudentList()

        studentAdapter = StudentAdapter(originalStudentList)
        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        studentRecyclerView.adapter = studentAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                val query = editable.toString().trim()
                if (query.length > 2) {
                    filterStudentList(query)
                } else {
                    studentAdapter.updateList(originalStudentList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun generateMockStudentList(): List<Student> {
        return listOf(
            Student("Kha Minh Bao", "20210098"),
            Student("Alice Johnson", "20210001"),
            Student("Bob Smith", "20210002"),
            Student("Cathy Brown", "20210003"),
            Student("Daniel Williams", "20210004"),
            Student("Emma Davis", "20210005"),
            Student("Frank Thomas", "20210006"),
            Student("Grace Moore", "20210007"),
            Student("Henry Taylor", "20210008"),
            Student("Isla Anderson", "20210009"),
            Student("Jack White", "20210010"),
            Student("Kara Harris", "20210011"),
            Student("Liam Martin", "20210012"),
            Student("Mia Jackson", "20210013"),
            Student("Noah Thompson", "20210014"),
            Student("Olivia Garcia", "20210015"),
            Student("Paul Martinez", "20210016"),
            Student("Quinn Robinson", "20210017"),
            Student("Rachel Clark", "20210018"),
            Student("Sam Lewis", "20210019"),
            Student("Tina Lee", "20210020"),
            Student("Umar Walker", "20210021"),
            Student("Vera Hall", "20210022"),
            Student("William Allen", "20210023"),
            Student("Xander Young", "20210024"),
            Student("Yara King", "20210025"),
            Student("Zoe Scott", "20210026")
        )
    }

    // Filter the student list based on the search query
    private fun filterStudentList(query: String) {
        val filteredList = originalStudentList.filter {
            it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
        }
        studentAdapter.updateList(filteredList)
    }
}
