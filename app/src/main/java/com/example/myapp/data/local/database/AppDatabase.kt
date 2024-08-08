package com.example.myapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.data.local.dao.AppDao
import com.example.myapp.data.model.Food
import com.example.myapp.data.model.Person
import com.example.myapp.data.model.PersonFoodCrossRef

@Database(entities = [Person::class, Food::class, PersonFoodCrossRef::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                .build()
    }
}
