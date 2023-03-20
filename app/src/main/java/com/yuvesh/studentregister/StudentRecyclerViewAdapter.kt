package com.yuvesh.studentregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yuvesh.studentregister.db.Student
import com.yuvesh.studentregister.db.StudentDao

class StudentRecyclerViewAdapter(
    private val clickListener:(Student)->Unit
):RecyclerView.Adapter<StudentViewHolder>() {
    val studentList=ArrayList<Student>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StudentViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return StudentViewHolder(layoutInflater)
    }


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {

        holder.bind(studentList[position],clickListener)

    }

    override fun getItemCount(): Int {
         return studentList.size
    }

    fun setList(student: List<Student>)
    {
        studentList.clear()
        studentList.addAll(student)
    }
}

class StudentViewHolder(private val view: View):RecyclerView.ViewHolder(view)
{
    fun bind(student: Student, clickListener:(Student)->Unit)
    {
        val nameTextView=view.findViewById<TextView>(R.id.tvname)
        val emailTextView=view.findViewById<TextView>(R.id.tvemail)
       // val phoneTextView=view.findViewById<TextView>(R.id.tvphone)
        nameTextView.text=student.name
        emailTextView.text=student.email
      //  phoneTextView.text=student.phone
        view.setOnClickListener{
            clickListener(student)
        }
    }
}