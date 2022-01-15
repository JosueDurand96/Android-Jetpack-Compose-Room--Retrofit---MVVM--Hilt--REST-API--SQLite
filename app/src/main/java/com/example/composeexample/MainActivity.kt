package com.example.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeexample.model.User
import com.example.composeexample.ui.theme.ComposeExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyAppPrincipal()
                }
            }
        }
    }
}

@Composable
fun MyAppPrincipal(
    viewModel: UserViewModel = hiltViewModel()
) {
    val users by viewModel.users.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)

    MyApp(onAddClick = {
        viewModel.addUser()
    },
        users = users,
        isLoading = isLoading
    )
}

@Composable
fun MyApp(
    onAddClick: (() -> Unit)? = null,
    users: List<User>,
    isLoading: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Simple Rest  + Room") },
                actions = {
                    IconButton(onClick = {
                        onAddClick?.invoke()
                    }) {
                        Icon(Icons.Filled.Add, "Add")
                    }
                }
            )
        }
    ) {
        LazyColumn{
            var itemCount = users.size
            if (isLoading) itemCount++

            items(count = itemCount) { index ->
                var auxIndex = index
                if (isLoading) {
                    if (auxIndex == 0 )
                        return@items Text(text = "IsLoading")
                    auxIndex--
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeExampleTheme {
        MyApp(onAddClick = null, users = listOf(), isLoading = false)
    }
}