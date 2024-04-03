package com.example.guessing_country_flags

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.json.JSONObject

//import com.example.guessing_country_flags.ui.theme.Guessing_Country_FlagsTheme

//This is the screen for the Guess Hints Game Screen

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity3 : ComponentActivity() {
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
                                text="COUNTRY FLAGS GAME",
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                },
            ) { innerPadding ->
                GuessHintsScreen(innerPadding)

            }

//            }
        }
    }
}

@Composable
fun GuessHintsScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val countriesJson = JSONObject(readTextFromAssets(context, "countries.json"))

    var selected_Country_by_index by remember { mutableStateOf(-1) }
    var user_submitted_Guess by remember { mutableStateOf(false) }
    var correct_guess by remember { mutableStateOf(false) }
    var random_country_code by remember { mutableStateOf(countriesJson.keys().asSequence().toList().random()) }

    // Get the country name corresponding to the random country code
    val randomCountryName = countriesJson.optString(random_country_code)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "what is this Flag ?", modifier = Modifier.padding(10.dp))

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = getDrawableResourceId(random_country_code.lowercase())),
            contentDescription = "Country Flag"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Pass all country names directly
//        CountryList(
//            countries =,
//            onCountrySelected = { index ->
//                selected_Country_by_index = index
//            }
//        )

        LazyColumn(Modifier.weight(1f)) {

            items(
                countriesJson.keys().asSequence().toList()
                    .map { countriesJson.optString(it) }.size
            ) { index ->
                CountryItem(
                    country = countriesJson.keys().asSequence().toList()
                        .map { countriesJson.optString(it) }[index],
                    onItemClick ={ selected_Country_by_index = index }
                )
            }
        }

        // Here, i implemented the Submit button .this button is only enabled when user selected a country from the country names list.
        Button(
            onClick = {
                if (selected_Country_by_index != -1) { // Check if a country is selected
                    correct_guess = selected_Country_by_index == countriesJson.keys().asSequence().indexOf(random_country_code)
                    user_submitted_Guess = true
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            enabled = selected_Country_by_index != -1 // Here, I implemented functionality to enable button only when a country is selected by the user.
        )
        {


            Text("Submit")
        }

        Button(
            onClick = {

                //let's generate a new random country code.
                val new_country_Random_code=countriesJson.keys().asSequence().toList().random()
                random_country_code=new_country_Random_code
                countriesJson.optString(new_country_Random_code)
                selected_Country_by_index=-1
                user_submitted_Guess=false
                correct_guess=false

            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            enabled = selected_Country_by_index != -1 // Enable button only when a country is selected
        ) {
            Text("Next")

        }

        // Display the random country name only if the guess is submitted
        if (user_submitted_Guess)
        {
            if (correct_guess)
            {
                Text(
                    text= buildAnnotatedString {
                        append("CONGRATULATIONS !!!")
                        withStyle(style = SpanStyle(color = Color.Blue))
                        {
                            append(countriesJson.optString(random_country_code))
                        }
                        append("   IS CORRECT.")
                    },
                    modifier = Modifier.padding(10.dp)

                )
            } else
            {
                Text(text = "SORRY, YOUR GUESS IS INCORRECT. THE CORRECT ANSWER IS  ${countriesJson.optString(random_country_code)}   .PLEASE TRY AGAIN.", modifier = Modifier.padding(10.dp), color = Color.Red)
            }
        }
    }
}

