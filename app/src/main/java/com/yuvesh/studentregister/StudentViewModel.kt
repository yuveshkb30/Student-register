package com.yuvesh.studentregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuvesh.studentregister.db.Student
import com.yuvesh.studentregister.db.StudentDao
import kotlinx.coroutines.launch

class StudentViewModel(private val dao: StudentDao):ViewModel() {

    val student=dao.getAllStudents()

    fun insertStudent(student: Student)=viewModelScope.launch {
        dao.insertStudent(student)
    }

    fun updateStudent(student: Student)=viewModelScope.launch {
        dao.updateStudent(student)
    }

    fun deleteStudent(student: Student)=viewModelScope.launch {
        dao.deleteStudent(student)
    }
}