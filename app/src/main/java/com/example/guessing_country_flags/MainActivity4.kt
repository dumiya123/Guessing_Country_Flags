package com.example.guessing_country_flags

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.json.JSONObject
import java.time.format.TextStyle

//import com.example.guessing_country_flags.ui.theme.Guessing_Country_FlagsTheme

class MainActivity4 : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Guessing_Country_FlagsTheme {
                // A surface container using the 'background' color from the theme
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title =
                        {
                            Text(
                                text="GUESS THE FLAG SCREEN",
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                },
            ) { innerPadding ->
                GuessFlagsScreen(innerPadding)

            }
//            }
        }
    }
}

//lets create the function for creating GuessFlagScreen Screen.
@Composable
fun GuessFlagsScreen(innerPadding: PaddingValues) 
{
    // Retrieve the context and load countries JSON data
    val context = LocalContext.current

    //Reading JSON data from assets file named countries.
    val countries_json = JSONObject(readTextFromAssets(context, "countries.json"))

    // Define mutable state variables to track game state
    var name_of_the_correct_country by remember {

        mutableStateOf("")

    }
    var name_of_the_selected_country by remember {

        mutableStateOf("")

    }
    var display_correct_message by remember {

        mutableStateOf(false)
    }
    var country_flags_pairs by remember {

        mutableStateOf(listOf<Pair<String, String>>())

    }

    // below code will  generate 3 unique random flag images and their corresponding countries
    fun generatecountry_flags_pairs(): List<Pair<String, String>>
    {
        val codes_of_countries = countries_json.keys().asSequence().toList()
        return codes_of_countries.shuffled().distinct().take(3).map { countryCode ->
            val name_of_the_country = countries_json.optString(countryCode)
            countryCode to name_of_the_country
        }
    }

    
    if (country_flags_pairs.isEmpty()) 
    {
        country_flags_pairs = generatecountry_flags_pairs()
        name_of_the_correct_country = country_flags_pairs.random().second
    }

    // this function will handle country flag click event
    fun onFlagClick(name_of_the_country: String) 
    {
        name_of_the_selected_country = name_of_the_country
        display_correct_message = true
    }

    // UI Composable starts here
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) 
    {
        // Display the correct country name
        Text(
            text = name_of_the_correct_country,
            modifier = Modifier.padding(bottom = 16.dp),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 24.sp, // Increase font size to 24 sp
                fontWeight = FontWeight.Bold // Apply bold style
            )
        )

        // this code will responsible for showing flag images vertically.
        country_flags_pairs.forEach { (countryCode, _) ->
            val resourceId = getDrawableResourceId(countryCode.lowercase())
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = "Country Flag",
                modifier = Modifier
                    .size(180.dp) // Adjust the size of flag images
                    .padding(2.dp) // Add some padding around each flag
                    .background(Color.LightGray)
                    .clickable { onFlagClick(countries_json.optString(countryCode)) }
            )
        }

        // Display the "Next" button
        Button(
            onClick = {
                //  below code will reset state for the next round for the user
                name_of_the_selected_country = ""
                display_correct_message = false
                // this will generate new set of flag-country pairs
                country_flags_pairs = generatecountry_flags_pairs()
                // select one of the country names as the correct answer.
                name_of_the_correct_country = country_flags_pairs.random().second
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Next")
        }

        // below code will display message based on user's click.
        if (display_correct_message)
        {
            val message = if (name_of_the_selected_country == name_of_the_correct_country) "CORRECT!" else "WRONG!"
            val color = if (name_of_the_selected_country == name_of_the_correct_country) Color.Green else Color.Red
            Text(
                text = message,
                modifier = Modifier.padding(top = 16.dp),
                color = color
            )
        }
    }
}























