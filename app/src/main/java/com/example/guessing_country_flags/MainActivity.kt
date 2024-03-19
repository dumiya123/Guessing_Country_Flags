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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guessing_country_flags.ui.theme.Guessing_Country_FlagsTheme
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.random.Random

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

@Composable
fun CountryFlagsApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("guessCountryGame") {
            GuessCountryGameScreen(navController)
        }
        composable("guessHintsGame") {
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
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
fun GuessCountryGameScreen(navController: NavController) {
    val context = LocalContext.current
    val countriesJsonArray = JSONObject( readTextFromAssets(context, "countries.json"))

    var selectedCountryIndex by remember { mutableStateOf(-1) }
//    val countryFlags = loadCountryFlags()
    val randomCountry = remember { countriesJsonArray.keys().asSequence().toList().random() }
//    val randomCountry = remember { countryFlags[randomIndex] }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Guess the Country Flag",modifier= Modifier.padding(10.dp))
        Image(
            painter = painterResource(id = getDrawableResourceId(randomCountry.lowercase())),
            contentDescription = "Country Flag"
        )
        Spacer(modifier = Modifier.height(16.dp))


//        Button(
//            onClick = {
//                if (selectedCountryIndex != -1) {
//                    val isCorrect =
//                    val message = if (isCorrect) "Correct!" else "Incorrect!"
////                    Toast.makeText(
////                        LocalContext.current,
////                        message,
////                        Toast.LENGTH_SHORT
////                    ).show()
//                }
//            },
//            modifier = Modifier
//                .padding(8.dp)
//                .fillMaxWidth()
//        ) {
//            Text("Submit")
//        }

        CountryList(
            countries = countriesJsonArray.keys().asSequence().toList().map { countriesJsonArray[it] as String }.toList(),
            onCountrySelected = { index ->
                selectedCountryIndex = index
            }
        )
    }
}

data class CountryFlag(val flagId: Int, val countryName: String)

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


//@Composable
//fun loadCountryFlags(): List<CountryFlag> {
//    val countryFlags = mutableListOf<CountryFlag>()
//    val context = LocalContext.current
////    Log.d("1", readTextFromAssets(context, "countries.json"))
//    val countriesJsonArray = JSONObject( readTextFromAssets(context, "countries.json"))
////    Log.d("json", countriesJsonArray["AE"].toString())
////    for (i in 0 until countriesJsonArray.length()) {
////        val countryKeys = countriesJsonArray.keys()
//////        val countryCode = countriesJsonArray[countryKeys[i]].toLowerCase(Locale.ROOT)
//////        val countryName = countriesJsonArray.getString("name")
////        val flagId = getDrawableResourceId(countriesJsonArray[countryKeys[i]].toString())
////        if (flagId != 0) {
////            countryFlags.add(CountryFlag(flagId, countryName))
////        }
////    }
////    return countryFlags
//}


fun getDrawableResourceId(countryCode: String): Int {
    return try {
        R.drawable::class.java.getField(countryCode).getInt(null)
    } catch (e: Exception) {
        0
    }
}

fun loadJsonStringFromAsset(fileName: String): String {
    return try {
        val inputStream = MainActivity::class.java.classLoader!!.getResourceAsStream(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (e: Exception) {
        ""
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















