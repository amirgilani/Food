package com.example.myapp.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapp.data.di.DataModule
import com.example.myapp.domain.di.DomainModule
import com.example.myapp.domain.repository.PersonRepository
import com.example.myapp.domain.usecase.AddPersonUseCase
import com.example.myapp.presentation.ui.theme.FoodTheme
import com.example.myapp.presentation.viewmodel.MainViewModel
import com.example.myapp.presentation.viewmodel.MyViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val shouldShowDialog = remember { mutableStateOf(false) }
                    var username by remember { mutableStateOf("") }
                    if (shouldShowDialog.value) {
                        MyAlertDialog(
                            title = {
                                Text(
                                    text = "Enter Name",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            },
                            content = {
                                OutlinedTextField(
                                    value = username,
                                    onValueChange = { username = it },
                                    label = { Text("Name") },
                                )
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = { shouldShowDialog.value = false },
                                    content = { Text("CANCEL") },
                                )
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        shouldShowDialog.value = false
                                    },
                                    content = { Text("OK") },
                                )
                            },
                            onDismiss = {
                                shouldShowDialog.value = false
                            },
                            shouldShowDialog = shouldShowDialog
                        )
                    }
                    MyButton("افزودن شخص",Alignment.Start){
                        shouldShowDialog.value = true
                    }
                    MyButton("افزودن غذا",Alignment.End){

                    }


                    PersonListScreen(viewModel = MainViewModel(
                        DomainModule.provideAddPersonUseCase(DataModule.providePersonRepository(DataModule.provideAppDao(DataModule.provideAppDatabase(
                            applicationContext)))),
                        DomainModule.provideAddFoodUseCase(DataModule.providePersonRepository(DataModule.provideAppDao(DataModule.provideAppDatabase(
                            applicationContext)))),
                        DomainModule.provideAddFavoriteFoodUseCase(DataModule.providePersonRepository(DataModule.provideAppDao(DataModule.provideAppDatabase(
                            applicationContext)))),
                        DomainModule.provideGetAllPersonsUseCase(DataModule.providePersonRepository(DataModule.provideAppDao(DataModule.provideAppDatabase(
                            applicationContext)))),
                        DomainModule.provideGetAllFoodsUseCase(DataModule.providePersonRepository(DataModule.provideAppDao(DataModule.provideAppDatabase(
                            applicationContext)))),
                        DomainModule.provideGetPersonWithFoodsUseCase(DataModule.providePersonRepository(DataModule.provideAppDao(DataModule.provideAppDatabase(
                            applicationContext))))
                    ))




                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



@Composable
fun MyButton(text:String,horizontal: Alignment.Horizontal,onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = horizontal,
    ) {
        val context = LocalContext.current
        Button(
            onClick = {
                     onClick.invoke()
            },
            modifier = Modifier.padding(16.dp),
            enabled = true,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Blue
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            ),
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif
            )
        }
    }
}


@Composable
fun MyAlertDialog(
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    confirmButton: @Composable () -> Unit,
    onDismiss: () -> Unit,
    shouldShowDialog: MutableState<Boolean>
) {
    if (shouldShowDialog.value){
        Dialog(onDismiss) {
            Surface(shape = MaterialTheme.shapes.medium) {
                Column {
                    Column(Modifier.padding(24.dp)) {
                        title.invoke()
                        Spacer(Modifier.size(16.dp))
                        content.invoke()
                    }
                    Spacer(Modifier.size(4.dp))
                    Row(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        Arrangement.spacedBy(8.dp, Alignment.End),
                    ) {
                        dismissButton.invoke()
                        confirmButton.invoke()
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodTheme {
        Greeting("Android")
    }
}