package com.example.materialweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.materialweather.ui.theme.MaterialWeatherTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
//                    GetCurrentWeather { viewModel.getCurrentWeather(location = it) }
                    LaunchedEffect(key1 = Unit) {
                        viewModel.getCurrentWeather("London")
                    }
                }
            }
        }
    }
}

@Composable
fun GetCurrentWeather(onClick: (location: String) -> Unit) {
    Button(onClick = { onClick("London") }, modifier = Modifier.wrapContentSize()) {
        Text(text = "Get Current Weather")
    }
}

@Preview
@Composable
private fun PreviewGetCurrentWeather() {
    GetCurrentWeather {}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialWeatherTheme {
        Greeting("Android")
    }
}