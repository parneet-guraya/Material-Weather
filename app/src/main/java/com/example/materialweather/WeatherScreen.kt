package com.example.materialweather

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.materialweather.data.Hour

@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {

    val screenState by weatherViewModel.uiState.collectAsStateWithLifecycle()

    var showChooseLocationDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (screenState.loading) {
        CircularProgressIndicator(modifier = Modifier.wrapContentSize())
    }

    if (showChooseLocationDialog) {
        SetLocationDialog(onDismiss = {
            showChooseLocationDialog = false
        }, onConfirmClick = { location ->
            weatherViewModel.getCurrentWeather(location)
            weatherViewModel.getHourlyForecast(location)
            showChooseLocationDialog = false
        })
    }

    screenState.screenData?.let { data ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            CurrentWeatherCard(
                currentWeatherIconLink = "https:${
                    data.current?.condition?.icon?.replace(
                        "64",
                        "128"
                    )
                }",
                currentWeatherCelsius = data.current?.tempC,
                currentWeatherStatus = data.current?.condition?.text,
                currentLocation = data.location?.name ?: "Location",
                lastUpdated = "Last Updated: ${data.current?.lastUpdated ?: "Unknown"}",
                onLocationClick = { showChooseLocationDialog = true }
            )

            data.forecast?.let { forecast ->
                Spacer(modifier = Modifier.height(8.dp))
                HourlyForecastRow(forecastHourList = forecast.forecastDay.first().hour.map {
                    it.copy(
                        condition = it.condition.copy(icon = "https:${it.condition.icon}"),
                        time = it.copy().time?.takeLast(5)
                    )
                })
            }
        }
    }

    screenState.errorMessage?.let { message ->
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT)
            .show()
        Text(text = "Couldn't find the location")
        Button(
            modifier = Modifier.wrapContentSize(),
            onClick = { showChooseLocationDialog = true }) {
            Text(text = "Set Another Location")
        }
    }

}


@Composable
fun CurrentWeatherCard(
    modifier: Modifier = Modifier,
    currentWeatherIconLink: String?,
    currentWeatherCelsius: Double?,
    currentWeatherStatus: String?,
    currentLocation: String,
    lastUpdated: String,
    onLocationClick: (value: String) -> Unit
) {
    Card {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentLocation,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(text = lastUpdated, style = MaterialTheme.typography.labelLarge)
                }
                IconButton(onClick = { onLocationClick(currentLocation) }) {
                    Image(
                        modifier = Modifier.fillMaxHeight(),
                        painter = painterResource(id = R.drawable.baseline_location_on_24),
                        contentDescription = null
                    )
                }
            }
            AsyncImage(
                modifier = Modifier
                    .size(128.dp),
                model = currentWeatherIconLink,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_launcher_background)
            )
            Text(text = "$currentWeatherCelsius C", style = MaterialTheme.typography.displayMedium)
            Text(text = "$currentWeatherStatus", style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
fun HourlyForecastRow(forecastHourList: List<Hour>) {
    Card {
        LazyRow {
            items(forecastHourList) {
                ForecastHourCard(
                    time = it.time,
                    iconLink = it.condition.icon,
                    averageTemp = it.tempC
                )
            }
        }
    }
}

@Composable
fun ForecastHourCard(
    modifier: Modifier = Modifier,
    time: String?,
    iconLink: String?,
    averageTemp: Double?
) {
    Column(
        modifier = Modifier.size(height = 160.dp, width = 80.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = time!!)
        AsyncImage(
            modifier = Modifier.size(64.dp),
            model = iconLink,
            contentDescription = null,
            placeholder = painterResource(
                id = R.drawable.ic_launcher_background
            )
        )
        Text(text = averageTemp.toString())
    }
}

@Composable
fun SetLocationDialog(onConfirmClick: (inputText: String) -> Unit, onDismiss: () -> Unit) {
    var textFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Choose Location",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = { Text(text = "Enter Location") })

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onConfirmClick(textFieldValue) }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ForecastHourCardPreview() {
    ForecastHourCard(
        Modifier.padding(8.dp),
        "12:30AM",
        "",
        24.0
    )
}

@Preview
@Composable
private fun ForecastHourRowPreview() {
//    HourlyForecastRow()
}

@Preview
@Composable
private fun SetLocationDialogPreview() {
    SetLocationDialog(onConfirmClick = { /*TODO*/ }) {

    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CurrentWeatherCardPreview() {
    CurrentWeatherCard(
        currentWeatherIconLink = "https://cdn.weatherapi.com/weather/64x64/night/113.png",
        currentWeatherCelsius = 22.3, currentWeatherStatus = "Rainy",
        currentLocation = "London",
        lastUpdated = "Last Updated: 12:05PM",
        onLocationClick = {})

}