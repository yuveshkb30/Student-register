package com.yuvesh.studentregister.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase: RoomDatabase() {

    abstract fun studentDao():StudentDao

    companion object{
        @Volatile
        private var INSTANCE: StudentDatabase?=null
        fun getInstance(context: Context):StudentDatabase{
            synchronized(this){
                var instance= INSTANCE
                if(instance==null)
                    {
                         instance=Room.databaseBuilder(
                             context.applicationContext,
                             StudentDatabase::class.java,
                             "student_data_database"
                         ).build()
                    }
                return instance
            }
        }
    }
}