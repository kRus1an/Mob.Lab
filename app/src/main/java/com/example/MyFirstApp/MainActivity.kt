package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                }
                    NavHost(navController = navController, startDestination = "start_screen") {
                        composable("start_screen") {
                            Start_Screen(navController)
                        }
                        composable("finish_screen") {
                            Finish_Screen(navController)
                        }
                    }

                }
            }
        }
    }
@Composable
fun Start_Screen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { navController.navigate("finish_screen") },
            content = {
                Text(
                    text = "Перейти на второй экран"
                )
            }
        )
    }

}

@Composable
fun Finish_Screen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { navController.popBackStack() },
            content = {
                Text(
                    text = "Вернуться на первый экран",
                )
            }
        )
    }

}}