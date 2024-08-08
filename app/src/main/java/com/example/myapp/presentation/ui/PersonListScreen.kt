package com.example.myapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import com.example.myapp.presentation.viewmodel.MainViewModel

@Composable
fun PersonListScreen(viewModel: MainViewModel) {
    val persons by viewModel.persons.observeAsState(emptyList())
    val selectedPerson by viewModel.selectedPerson.observeAsState(null)
    val foods by viewModel.foods.observeAsState(emptyList())

    Column {
        LazyColumn {
            items(persons) { person ->
                Text(person.name, modifier = Modifier.clickable {
                    viewModel.selectPerson(person.id)
                })
            }
        }

        selectedPerson?.let { personWithFoods ->
            Text(text = "Favorite Foods for ${personWithFoods.person.name}")
            LazyColumn {
                items(foods) { food ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.toggleFavoriteFood(personWithFoods.person.id, food.id)
                            }
                    ) {
                        Text(food.name)
                        Checkbox(
                            checked = personWithFoods.foods.contains(food),
                            onCheckedChange = null
                        )
                    }
                }
            }
        }
    }
}
