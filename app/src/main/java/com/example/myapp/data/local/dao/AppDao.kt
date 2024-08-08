package com.example.myapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapp.data.model.Food
import com.example.myapp.data.model.PersonFoodCrossRef
import com.example.myapp.data.model.PersonWithFoods
import com.example.myapp.domain.model.Person

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: com.example.myapp.data.model.Person)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: com.example.myapp.data.model.Food)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteFood(personId: Long, foodId: Long)

    @Query("SELECT * FROM person")
    suspend fun getAllPersons(): List<com.example.myapp.data.model.Person>

    @Query("SELECT * FROM food")
    suspend fun getAllFoods(): List<com.example.myapp.data.model.Food>

    @Transaction
    @Query("SELECT * FROM person WHERE id = :personId")
    suspend fun getPersonWithFoods(personId: Long): com.example.myapp.data.model.PersonWithFoods
}
