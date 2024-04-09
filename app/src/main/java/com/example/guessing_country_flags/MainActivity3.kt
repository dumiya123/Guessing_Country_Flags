package com.example.guessing_country_flags

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.json.JSONObject

//import com.example.guessing_country_flags.ui.theme.Guessing_Country_FlagsTheme

//This is the screen for the Guess Hints Game Screen .


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
                                    text="GUESS HINTS SCREEN",

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

/**
 * This function creates the guess hints game screen.
 */
@Composable
fun GuessHintsScreen(innerPadding: PaddingValues)
{
    //obtaining the current context.
    val context = LocalContext.current

    //Reading JSON data from assets file named countries.
    val countriesJson = JSONObject(readTextFromAssets(context, "countries.json"))

    //variables to hold different states of the UI

    var random_country_code by rememberSaveable{

        mutableStateOf(countriesJson.keys().asSequence().toList().random())
    }

    var name_of_hidden_country by rememberSaveable {

        mutableStateOf(countriesJson.optString(random_country_code))
    }

    var user_guessed_name by rememberSaveable {

        mutableStateOf("")
    }

    var user_submitted_Guess by rememberSaveable {

        mutableStateOf(false)
    }

    var current by rememberSaveable {

        mutableStateOf("_ ".repeat(name_of_hidden_country.length))

    }
    var Error_message by rememberSaveable {

        mutableStateOf("")
    }

    var show_error by rememberSaveable {

        mutableStateOf(false)
    }

    var user_incorrect_guesses by rememberSaveable {

        mutableStateOf(0)
    }
    var game_ended by rememberSaveable {

        mutableStateOf(false)
    }


    // Composable column to layout UI components vertically.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(text = "What is this the Flag ?", modifier = Modifier.padding(10.dp),fontSize = 20.sp,style = TextStyle(fontWeight = FontWeight.Bold))

        Spacer(modifier = Modifier.height(30.dp))


        //This line will show a random flag to the screen.
        Image(
            painter = painterResource(id = getDrawableResourceId(random_country_code.lowercase())),
            contentDescription = "Country Flag"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display dashes representing the hidden country name.
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = current,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Text field for character input
        TextField(
            value = user_guessed_name,
            onValueChange = { new_value ->
                if (!game_ended) {
                    if (new_value.length > user_guessed_name.length)
                    {
                        val user_new_guessed_char = new_value.last().uppercase() // Ensure uppercase for case-insensitive matching
                        val new_current_state = buildAnnotatedString{
                            for (i in name_of_hidden_country.indices)
                            {
                                if (user_new_guessed_char == name_of_hidden_country[i].uppercase())
                                {
                                    append(name_of_hidden_country[i])
                                } else
                                {
                                    append(current[i * 2])
                                }
                                if (i < name_of_hidden_country.length - 1)
                                {
                                    append(" ")
                                }
                            }
                        }
                        current = new_current_state.toString()
                        if (!name_of_hidden_country.uppercase().contains(user_new_guessed_char))
                        {
                            user_incorrect_guesses++
                            if (user_incorrect_guesses >= 3)
                            {
                                game_ended = true
                                Error_message = "WRONG!"
                                show_error = true
                            }
                        }
                    }
                    user_guessed_name = new_value
                }
            },
            label = { Text("Enter your guess here (Hint:Enter a character)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            maxLines = 1
        )

        //Button for submitting and go the next step

        Button(
            onClick = {
                if (user_guessed_name.isNotEmpty())
                {
                    if (!game_ended)
                    {
                        user_submitted_Guess = true
                    }
                    else
                    {
                        // below code will start a new game
                        random_country_code = countriesJson.keys().asSequence().toList().random()
                        name_of_hidden_country = countriesJson.optString(random_country_code)
                        user_guessed_name = ""
                        current = "_ ".repeat(name_of_hidden_country.length)
                        Error_message = ""
                        show_error = false
                        user_incorrect_guesses = 0
                        game_ended = false
                    }
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            enabled = user_guessed_name.isNotEmpty() // this line will enable button only when a character is entered
        ) {
            Text(if (game_ended) "Next" else "Submit")
        }

        if (show_error) {
            Text(
                text = Error_message,
                modifier = Modifier.padding(10.dp),
                color = Color.Red
            )
        }

        //this line handle submitted guess by user.
        if (user_submitted_Guess)
        {
            val correct_guess = current.replace(" ", "") == name_of_hidden_country
            if (correct_guess)
            {
                Error_message = "CORRECT!"
                game_ended = true
            }
            user_submitted_Guess = false
        }

        //Display a message if the game ended.
        if (game_ended)
        {
            Text(
                text = Error_message,
                modifier = Modifier.padding(10.dp),
                color = if (Error_message == "CORRECT!") Color.Green else Color.Blue
            )
        }
    }
}







