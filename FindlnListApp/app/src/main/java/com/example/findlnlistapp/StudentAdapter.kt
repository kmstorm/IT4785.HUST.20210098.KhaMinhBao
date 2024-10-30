package com.example.findlnlistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        val studentIdTextView: TextView = itemView.findViewById(R.id.tvMssv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.nameTextView.text = student.name
        holder.studentIdTextView.text = student.mssv
    }

    override fun getItemCount(): Int = studentList.size

    // Efficiently update the list using DiffUtil
    fun updateList(newList: List<Student>) {
        val diffCallback = StudentDiffCallback(studentList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        studentList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}

// DiffUtil.Callback implementation to optimize list updates
class StudentDiffCallback(
    private val oldList: List<Student>,
    private val newList: List<Student>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Assuming each student has a unique mssv (ID) that can be used to identify them
        return oldList[oldItemPosition].mssv == newList[newItemPosition].mssv
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Compare all fields to check if content is the same
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
