package com.example.guessing_country_flags

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guessing_country_flags.ui.theme.Guessing_Country_FlagsTheme
import org.json.JSONObject
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Guessing_Country_FlagsTheme {
                CountryFlagsApp()

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryFlagsApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home")
        {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(
                                text="COUNTRY FLAGS GAME",
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                },
            ) { innerPadding ->
                HomeScreen(navController, innerPadding)

            }

        }
        composable("guessCountryGame")
        {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(
                                text="Guess Country Flags",
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                },
            ) { innerPadding ->
                GuessCountryGameScreen(navController,innerPadding)

            }

        }
        composable("guessHintsGame")
        {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(
                                text="Guess Country Flags",
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                },
            ) { innerPadding ->
                GuessCountryGameScreen(navController,innerPadding)

            }


            GuessHintsGameScreen(navController)
        }
        composable("guessFlagGame") {
            GuessFlagGameScreen(navController)
        }
        composable("advancedLevelGame") {
            AdvancedLevelGameScreen(navController)
        }
    }
}

@Composable
fun AdvancedLevelGameScreen(navController: NavHostController) {
    // Not implemented yet
}

@Composable
fun GuessFlagGameScreen(navController: NavHostController) {
    // Not implemented yet
}

@Composable
fun GuessHintsGameScreen(navController: NavHostController) {
    // Not implemented yet
}

@Composable
fun HomeScreen(navController: NavController, innerPadding: PaddingValues) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigate("guessCountryGame")
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Guess the Country")
        }

        Button(
            onClick = {
                navController.navigate("guessHintsGame")
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Guess-Hints")
        }

        Button(
            onClick = {
                navController.navigate("guessFlagGame")
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Guess the Flag")
        }

        Button(
            onClick = {
                navController.navigate("advancedLevelGame")
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Advanced Level")
        }
    }
}

@Composable
fun GuessCountryGameScreen(navController: NavController, innerPadding: PaddingValues) {
    val context = LocalContext.current
    val countriesJson = JSONObject(readTextFromAssets(context, "countries.json"))

    var selectedCountryIndex by remember { mutableStateOf(-1) }
    var submittedGuess by remember { mutableStateOf(false) }
    var correctGuess by remember { mutableStateOf(false) }
    val randomCountryCode by remember { mutableStateOf(countriesJson.keys().asSequence().toList().random()) }

    // Get the country name corresponding to the random country code
    val randomCountryName = countriesJson.optString(randomCountryCode)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Guess the Country Flag", modifier = Modifier.padding(10.dp))

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = getDrawableResourceId(randomCountryCode.lowercase())),
            contentDescription = "Country Flag"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Pass all country names directly
        CountryList(
            countries = countriesJson.keys().asSequence().toList().map { countriesJson.optString(it) },
            onCountrySelected = { index ->
                selectedCountryIndex = index
            }
        )


        // Submit button, enabled only when a country is selected
        Button(
            onClick = {
                if (selectedCountryIndex != -1) { // Check if a country is selected
                    correctGuess = selectedCountryIndex == countriesJson.keys().asSequence().indexOf(randomCountryCode)
                    submittedGuess = true
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            enabled = selectedCountryIndex != -1 // Enable button only when a country is selected
        ) {
            Text("Submit")
        }

        // Display the random country name only if the guess is submitted
        if (submittedGuess) {
            if (correctGuess) {
                Text(text = "Congratulations! Your guess is correct.", modifier = Modifier.padding(10.dp))
            } else {
                Text(text = "Sorry, your guess is incorrect. The correct answer was ${countriesJson.optString(randomCountryCode)}.", modifier = Modifier.padding(10.dp))
            }
        }
    }
}



@Composable
fun CountryList(countries: List<String>, onCountrySelected: (Int) -> Unit) {

    LazyColumn {
        items(countries.size) { index ->
            CountryItem(
                country = countries[index],
                onItemClick = { onCountrySelected(index) }
            )
        }
    }
}

@Composable
fun CountryItem(country: String, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
    ) {
        Text(
            text = country,
            modifier = Modifier.padding(16.dp)
        )
    }
}


fun getDrawableResourceId(countryCode: String): Int {
    return try {
        R.drawable::class.java.getField(countryCode).getInt(null)
    } catch (e: Exception) {
        0
    }
}

fun readTextFromAssets(context: Context, fileName: String): String {
    return try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (e: IOException) {
        Log.e("AssetReader", "Error reading $fileName from assets", e)
        ""
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CountryFlagsApp()
}

