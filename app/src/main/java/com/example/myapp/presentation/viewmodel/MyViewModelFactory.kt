package com.example.myapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.GetAllPersonsUseCase
import com.example.myapp.domain.usecase.AddFavoriteFoodUseCase
import com.example.myapp.domain.usecase.AddFoodUseCase
import com.example.myapp.domain.usecase.AddPersonUseCase
import com.example.myapp.domain.usecase.GetAllFoodsUseCase
import com.example.myapp.domain.usecase.GetPersonWithFoodsUseCase

class MyViewModelFactory(private val addPersonUseCase: AddPersonUseCase,
                         private val addFoodUseCase: AddFoodUseCase,
                         private val addFavoriteFoodUseCase: AddFavoriteFoodUseCase,
                         private val getAllPersonsUseCase: GetAllPersonsUseCase,
                         private val getAllFoodsUseCase: GetAllFoodsUseCase,
                         private val getPersonWithFoodsUseCase: GetPersonWithFoodsUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(addPersonUseCase,addFoodUseCase, addFavoriteFoodUseCase, getAllPersonsUseCase, getAllFoodsUseCase, getPersonWithFoodsUseCase) as T
}