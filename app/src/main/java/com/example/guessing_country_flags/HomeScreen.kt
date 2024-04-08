package com.example.guessing_country_flags

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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



@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current

    var timerEnabled by remember {

        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.LightGray),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Switch(
            checked = timerEnabled,
            onCheckedChange = { timerEnabled = it }, // Toggle timer flag
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {

                val i = Intent(context, MainActivity2::class.java)
                context.startActivity(i)
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("GUESS THE COUNTRY")
        }

        Button(
            onClick = {

                val j = Intent(context, MainActivity3::class.java)
                context.startActivity(j)
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("GUESS HINTS")
        }

        Button(
            onClick = {
                val k=Intent(context,MainActivity4::class.java)
                context.startActivity(k)

            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("GUESS THE FLAG")
        }

        Button(
            onClick = {
                      val l=Intent(context,MainActivity5::class.java)
                context.startActivity(l)

            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("ADVANCED LEVEL")
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CountryFlagsApp()
//}

