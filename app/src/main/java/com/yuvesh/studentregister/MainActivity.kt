package com.yuvesh.studentregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuvesh.studentregister.db.Student
import com.yuvesh.studentregister.db.StudentDatabase

class MainActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var emailEditText: EditText
   // lateinit var phoneEditText:EditText
    lateinit var saveButton: Button
    lateinit var deleteButton: Button
    lateinit var studentRecyclerView: RecyclerView
    lateinit var recyclerViewAdapter: StudentRecyclerViewAdapter
    lateinit var selectedStudent: Student
    private var isListItemClicked= false


    private lateinit var viewModel:StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText=findViewById(R.id.etName)
        emailEditText=findViewById(R.id.etEmail)
       // phoneEditText=findViewById(R.id.etPhone)
        saveButton=findViewById(R.id.btnSave)
        deleteButton=findViewById(R.id.btnClear)
        studentRecyclerView=findViewById(R.id.rvstudent)

        val dao=StudentDatabase.getInstance(application).studentDao()
        val factory=StudentViewModelFactory(dao)

        viewModel=ViewModelProvider(this,factory).get(StudentViewModel::class.java)

          saveButton.setOnClickListener {

              if(isListItemClicked)
              {
                   updateStudent()
                  clearInput()
              }
              else
              {
                   saveStudentData()
                  clearInput()
              }

          }

        deleteButton.setOnClickListener {

            if(isListItemClicked)
            {
                deleteStudent()
                clearInput()
            }
            else
            {

                clearInput()
            }

        }

        initRecyclerView()
        displayStudent()
    }

    private fun initRecyclerView() {
        studentRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
        recyclerViewAdapter= StudentRecyclerViewAdapter{
              selectedItem:Student->listItemClicked(selectedItem)
        }

        studentRecyclerView.adapter=recyclerViewAdapter
    }

    private fun displayStudent(){
        viewModel.student.observe(this@MainActivity) {
            recyclerViewAdapter.setList(it)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    private fun updateStudent()
    {
        viewModel.updateStudent(
            Student(
                       selectedStudent.id,
                nameEditText.text.toString(),
                emailEditText.text.toString(),
               //  phoneEditText.text.toString()
            )
        )

        saveButton.text="Save"
        deleteButton.text="Delete"
        isListItemClicked=false
    }

    private fun deleteStudent()
    {
        viewModel.deleteStudent(
            Student(
                selectedStudent.id,
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                //phoneEditText.text.toString()
            )
        )

        saveButton.text="Save"
        deleteButton.text="Delete"
        isListItemClicked=false
    }

    private fun saveStudentData()
    {

         viewModel.insertStudent(
        Student(
            0,
            nameEditText.text.toString(),
            emailEditText.text.toString(),
            //phoneEditText.text.toString()

        )
                )
    }

    private fun clearInput()
    {
        nameEditText.setText("")
        emailEditText.setText("")
       // phoneEditText.setText("")
    }

    private fun listItemClicked(student: Student)
    {
        // Toast.makeText(this,"Student name is ${student.name}",Toast.LENGTH_LONG).show()

        selectedStudent=student
        saveButton.text="Update"
        deleteButton.text="Delete"

        isListItemClicked=true

         nameEditText.setText(selectedStudent.name)
        emailEditText.setText(selectedStudent.email)
      //  phoneEditText.setText(selectedStudent.phone)

    }
}