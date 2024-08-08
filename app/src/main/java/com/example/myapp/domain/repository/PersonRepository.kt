package com.example.myapp.domain.repository


import com.example.myapp.domain.model.Person
import com.example.myapp.domain.model.Food
import com.example.myapp.domain.model.PersonWithFoods

interface PersonRepository {
    suspend fun addPerson(person: com.example.myapp.data.model.Person)
    suspend fun addFood(food: com.example.myapp.data.model.Food)
    suspend fun addFavoriteFood(personId: Long, foodId: Long)
    suspend fun getAllPersons(): List<com.example.myapp.data.model.Person>
    suspend fun getAllFoods(): List<com.example.myapp.data.model.Food>
    suspend fun getPersonWithFoods(personId: Long): com.example.myapp.data.model.PersonWithFoods
}
