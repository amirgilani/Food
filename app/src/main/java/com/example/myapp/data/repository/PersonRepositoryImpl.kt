package com.example.myapp.data.repository


import com.example.myapp.domain.model.Person
import com.example.myapp.domain.model.Food
import com.example.myapp.domain.model.PersonWithFoods
import com.example.myapp.domain.repository.PersonRepository
import com.example.myapp.data.local.dao.AppDao

class PersonRepositoryImpl(private val dao: AppDao) : PersonRepository {
    override suspend fun addPerson(person: com.example.myapp.data.model.Person) {
        dao.insertPerson(person)
    }

    override suspend fun addFood(food: com.example.myapp.data.model.Food) {
        dao.insertFood(food)
    }

    override suspend fun addFavoriteFood(personId: Long, foodId: Long) {
        dao.insertFavoriteFood(personId, foodId)
    }

    override suspend fun getAllPersons(): List<com.example.myapp.data.model.Person> {
        return dao.getAllPersons()
    }

    override suspend fun getAllFoods(): List<com.example.myapp.data.model.Food> {
        return dao.getAllFoods()
    }

    override suspend fun getPersonWithFoods(personId: Long): com.example.myapp.data.model.PersonWithFoods {
        return dao.getPersonWithFoods(personId)
    }
}
