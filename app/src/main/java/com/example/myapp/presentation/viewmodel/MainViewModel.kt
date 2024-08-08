package com.example.myapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetAllPersonsUseCase
import com.example.myapp.data.model.Food
import com.example.myapp.data.model.Person
import com.example.myapp.data.model.PersonWithFoods
import com.example.myapp.domain.usecase.AddFavoriteFoodUseCase
import com.example.myapp.domain.usecase.AddFoodUseCase
import com.example.myapp.domain.usecase.AddPersonUseCase
import com.example.myapp.domain.usecase.GetAllFoodsUseCase
import com.example.myapp.domain.usecase.GetPersonWithFoodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addPersonUseCase: AddPersonUseCase,
    private val addFoodUseCase: AddFoodUseCase,
    private val addFavoriteFoodUseCase: AddFavoriteFoodUseCase,
    private val getAllPersonsUseCase: GetAllPersonsUseCase,
    private val getAllFoodsUseCase: GetAllFoodsUseCase,
    private val getPersonWithFoodsUseCase: GetPersonWithFoodsUseCase
) : ViewModel() {

    private val _persons = MutableLiveData<List<com.example.myapp.data.model.Person>>()
    val persons: LiveData<List<com.example.myapp.data.model.Person>> get() = _persons

    private val _foods = MutableLiveData<List<com.example.myapp.data.model.Food>>()
    val foods: LiveData<List<Food>> get() = _foods

    private val _personWithFoods = MutableLiveData<PersonWithFoods>()
    val personWithFoods: LiveData<PersonWithFoods> get() = _personWithFoods

    private val _selectedPerson = MutableLiveData<PersonWithFoods?>()
    val selectedPerson: LiveData<PersonWithFoods?> get() = _selectedPerson







    fun addPerson(person: Person) = viewModelScope.launch {
        addPersonUseCase.execute(person)
        loadPersons()
    }

    fun addFood(food: Food) = viewModelScope.launch {
        addFoodUseCase.execute(food)
        loadFoods()
    }

    fun addFavoriteFood(personId: Long, foodId: Long) = viewModelScope.launch {
        addFavoriteFoodUseCase.execute(personId, foodId)
        loadPersonWithFoods(personId)
    }

    fun loadPersons() = viewModelScope.launch {
        _persons.value = getAllPersonsUseCase.execute()
    }

    fun loadFoods() = viewModelScope.launch {
        _foods.value = getAllFoodsUseCase.execute()
    }

    fun loadPersonWithFoods(personId: Long) = viewModelScope.launch {
        _personWithFoods.value = getPersonWithFoodsUseCase.execute(personId)
    }

    fun selectPerson(personId: Long) {
       // _selectedPerson.value = _persons.value?.firstOrNull { it.id == personId }
    }

    fun toggleFavoriteFood(personId: Long, foodId: Long) = viewModelScope.launch {
        val personWithFoods = _selectedPerson.value ?: return@launch
        val isFavorite = personWithFoods.foods.any { it.id == foodId }

        if (isFavorite) {
            // Remove from favorites (implementation depends on the repository)
        } else {
            addFavoriteFood(personId, foodId)
        }

        loadPersonWithFoods(personId)
    }
}
