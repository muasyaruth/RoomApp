package com.example.room.studentdata

import androidx.lifecycle.LiveData

class StudentRepository(private val studentDao: StudentDao) {
    val readAllData:LiveData<List<Student>> =studentDao.readAllData()

    suspend  fun addStudent(student: Student){
        studentDao.addStudent(student)
    }

    suspend fun updateStudent(student: Student){
        studentDao.updateStudent(student)
    }
    suspend fun deleteStudent(student: Student){
        studentDao.deleteStudent(student)
    }
}