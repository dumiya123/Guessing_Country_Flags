/**
 * Video demonstration link -https://drive.google.com/file/d/1ObdQd5pgHdj9iiRX8rfEbb7xQtnhvjLc/view?usp=sharing
 */

package com.example.guessing_country_flags

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//import com.example.guessing_country_flags.ui.theme.Guessing_Country_FlagsTheme



class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Guessing_Country_FlagsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(
                                    text="WELCOME TO HOME!!!",
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                    },
                ) { innerPadding ->

                    HomeScreen(innerPadding)

                }

//            }

        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CountryFlagsApp() {
//    val navController = rememberNavController()
//
//    NavHost(navController, startDestination = "home") {
//        composable("home")
//        {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        colors = TopAppBarDefaults.topAppBarColors(
//                            containerColor = MaterialTheme.colorScheme.primaryContainer,
//                            titleContentColor = MaterialTheme.colorScheme.primary,
//                        ),
//                        title = {
//                            Text(
//                                text="COUNTRY FLAGS GAME",
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    )
//                },
//            ) { innerPadding ->
//                HomeScreen(navController, innerPadding)
//
//            }
//
//        }
//        composable("guessCountryGame")
//        {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        colors = TopAppBarDefaults.topAppBarColors(
//                            containerColor = MaterialTheme.colorScheme.primaryContainer,
//                            titleContentColor = MaterialTheme.colorScheme.primary,
//                        ),
//                        title = {
//                            Text(
//                                text="Guess Country Flags",
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    )
//                },
//            ) { innerPadding ->
//                GuessCountryGameScreen(navController,innerPadding)
//
//            }
//
//        }
//        composable("guessHintsGame")
//        {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        colors = TopAppBarDefaults.topAppBarColors(
//                            containerColor = MaterialTheme.colorScheme.primaryContainer,
//                            titleContentColor = MaterialTheme.colorScheme.primary,
//                        ),
//                        title = {
//                            Text(
//                                text="Guess Country Flags",
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    )
//                },
//            ) { innerPadding ->
//                GuessCountryGameScreen(navController,innerPadding)
//
//            }
//
//
//            GuessHintsGameScreen(navController)
//        }
//        composable("guessFlagGame") {
//            GuessFlagGameScreen(navController)
//        }
//        composable("advancedLevelGame") {
//            AdvancedLevelGameScreen(navController)
//        }
//    }
//}

/**
 * This function will responsible for the HomeScreen UI.
 */
@Composable
fun HomeScreen(innerPadding: PaddingValues) {

    val red = 255
    val green = 0
    val blue = 255
    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.DarkGray)
            .verticalScroll(rememberScrollState())
        ,

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        SwitchMinimalExample()

        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp) // Adjust size as needed
                .padding(bottom = 8.dp) // Add padding to separate image and text
        )

        Text(
            text = "LET'S GET STARTED!!!",
            modifier = Modifier.padding(vertical = 42.dp), // Increase vertical padding to move the text more upward
            fontSize = 28.sp, // Increase the font size
            fontWeight = FontWeight.Bold, // Make the font bold
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            color = Color(red, green, blue),
            textAlign = TextAlign.Center // Center align the text horizontally
        )

        Spacer(modifier = Modifier.height(25.dp))



        Button(
            onClick = {

                val i = Intent(context, MainActivity2::class.java)   //navigate to the "Guess the Country" activity
                context.startActivity(i)
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.LightGray)

        ) {
            Text(
                text="GUESS THE COUNTRY",
                fontWeight = FontWeight.Bold,
                color = Color.Black,

            )

        }

        Button(
            onClick = {

                val j = Intent(context, MainActivity3::class.java)  //navigate to the "GUESS HINTS" activity
                context.startActivity(j)
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth() ,
            colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
            Text(
                text="GUESS HINTS",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        }

        Button(
            onClick = {
                val k=Intent(context,MainActivity4::class.java) //navigate to the "GUESS THE FLAG" activity.
                context.startActivity(k)

            },

            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
            Text(
                text="GUESS THE FLAG",
                fontWeight = FontWeight.Bold,
                color = Color.Black,

            )
        }

        Button(
            onClick = {
                      val l=Intent(context,MainActivity5::class.java) //navigate to the "ADVANCED LEVEL" activity.
                context.startActivity(l)

            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
            Text(
                text="ADVANCED LEVEL",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text="©Developed by Dumindu Gamage.",
            fontWeight = FontWeight.Bold

        )
    }
}


//To create this switch button i refer https://developer.android.com/develop/ui/compose/components/switch documentation.
@Composable
fun SwitchMinimalExample() {
    var checked by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopEnd
    ) {
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
}

// @Preview(showBackground = true)
// @Composable
// fun DefaultPreview() {
// CountryFlagsApp()
// }


