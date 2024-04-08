package com.example.guessing_country_flags

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.guessing_country_flags.ui.theme.Guessing_Country_FlagsTheme
import org.json.JSONObject

class MainActivity5 : ComponentActivity() {
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
                                text="THE ADVANCED FLAG SCREEN",
                                textAlign = TextAlign.Center

                            )
                        }
                    )
                },
            ) { innerPadding ->
                AdvancedFlagsScreen(innerPadding)

            }

            }
//        }
    }
}

@Composable
fun AdvancedFlagsScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val countries_json = JSONObject(readTextFromAssets(context, "countries.json"))

    var correct_answers_of_user by remember {

        mutableStateOf(0)


    }
    var user_incorrect_attempts by remember {

        mutableStateOf(0)

    }
    var user_submitted_guess by remember {

        mutableStateOf(false)

    }
    var guesses_by_user by remember {

        mutableStateOf(listOf("", "", ""))

    }
    var country_flag_Pairs by remember {

        mutableStateOf(listOf<Pair<String, String>>())

    }
    var User_score by remember {

        mutableStateOf(0)

    }
    var Indices_of_correct_country by remember {

        mutableStateOf<List<Int>>(listOf())

    }
    var show_country_names by remember {

        mutableStateOf(false)
    }

    // Function to generate 3 unique random flag images and their corresponding countries
    fun generatecountry_flag_Pairs(): List<Pair<String, String>> {
        val Codes_of_countries = countries_json.keys().asSequence().toList()
        return Codes_of_countries.shuffled().distinct().take(3).map { countryCode ->
            val name_of_the_Country = countries_json.optString(countryCode)
            countryCode to name_of_the_Country
        }
    }

    // Initialize flag-country pairs if not already initialized
    if (country_flag_Pairs.isEmpty()) {
        country_flag_Pairs = generatecountry_flag_Pairs()
    }

    // Function to handle submission of user answers
    fun onSubmit() {
        user_submitted_guess = true
        correct_answers_of_user = 0
        Indices_of_correct_country = mutableListOf()

        country_flag_Pairs.forEachIndexed { index, pair ->
            val userGuess = guesses_by_user[index].trim() // Remove leading and trailing whitespaces
            val correctname_of_the_Country = pair.second.trim() // Remove leading and trailing whitespaces
            if (userGuess.equals(correctname_of_the_Country, ignoreCase = true)) {
                correct_answers_of_user++
                Indices_of_correct_country = Indices_of_correct_country.toMutableList().apply {
                    add(index)
                }
            }
        }

        // Increment the User_score by the number of correct answers
        User_score += correct_answers_of_user

        if (correct_answers_of_user < 3) {
            user_incorrect_attempts++
        }

        // After 3 incorrect attempts, reset game state
        if (user_incorrect_attempts >= 3) {
            user_incorrect_attempts = 0
            user_submitted_guess = false
            guesses_by_user = listOf("", "", "")
            country_flag_Pairs = generatecountry_flag_Pairs()
            show_country_names = true
        }
    }

    //In here starting UI components declaration
    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding).background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        country_flag_Pairs.forEachIndexed { index, (countryCode, _) ->
            val resourceId = getDrawableResourceId(countryCode.lowercase())
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = resourceId),
                    contentDescription = "Country Flag",
                    modifier = Modifier.size(130.dp)
                )
                TextField(
                    value = guesses_by_user[index],
                    onValueChange = {
                        guesses_by_user = guesses_by_user.toMutableList().apply { set(index, it) }
                    },
                    enabled = !user_submitted_guess && user_incorrect_attempts < 3,
                    textStyle = TextStyle(
                        color = if (user_submitted_guess && Indices_of_correct_country.contains(index)) Color.Blue else Color.Red
                    ),
                    modifier = Modifier
                        .width(300.dp)
                        .background(if (user_submitted_guess) Color.LightGray else Color.White)
                        .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
                    label = { Text("Enter country name") }
                )
            }
        }

        // Declare a Submit button to  submit user guessing
        Button(
            onClick = {
                if (!user_submitted_guess) {
                    onSubmit()
                } else {
                    // Reset the state for the next round
                    user_submitted_guess = false
                    guesses_by_user = listOf("", "", "") // Clear user guesses
                    country_flag_Pairs = generatecountry_flag_Pairs() // Generate new flag-country pairs
                    show_country_names = false
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(if (!user_submitted_guess) "Submit" else "Next")
        }

        // Display correctness message if user_submitted_guess
        if (user_submitted_guess)
        {
            val message = if (correct_answers_of_user == 3) "CORRECT!" else "WRONG!"
            val color = if (correct_answers_of_user == 3) Color.Green else Color.Red
            Text(
                text = message,
                modifier = Modifier.padding(top = 16.dp),
                color = color
            )

            if (show_country_names) {
                val correctname_of_the_Countrys = country_flag_Pairs
                    .joinToString(", ") { it.second }
                Text(
                    text = "Names of Correct Countries are: $correctname_of_the_Countrys",
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Blue
                )
            }
        }

        // Display user's User_score
        Text(
            text = "Your score: $User_score",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}













