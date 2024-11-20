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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.MyFirstApp.ui.theme.MyApplicationTheme
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DisplayCalculator()
                }
            }
        }
    }
}

@Composable
fun DisplayCalculator() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = result,
            onValueChange = { result = it },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(fontSize = 24.sp),
            placeholder = { Text("0") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        val buttons = listOf(
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf(".", "0", "=", "+")
        )

        Button(
            onClick = {
                input = ""
                result = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Clear")
        }

        buttons.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { label ->
                    Buttons(label, Modifier.weight(1f)) {
                        if (label == "=") {
                            result = evaluateExpression(input)
                        } else {
                            input += label
                            result = input
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun Buttons(label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(4.dp)
    ) {
        Text(text = label, fontSize = 24.sp)
    }
}

fun evaluateExpression(input: String): String {
    return try {
        val expression = input.replace("×", "*").replace("÷", "/")
        val result = ExpressionEvaluator.evaluate(expression)
        result.toString()
    } catch (e: Exception) {
        "Ошибка"
    }
}

object ExpressionEvaluator {
    fun evaluate(expression: String): Double {
        return try {
            val exp = ExpressionBuilder(expression).build()
            exp.evaluate()
        } catch (e: Exception) {
            0.0
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        DisplayCalculator()
    }
}