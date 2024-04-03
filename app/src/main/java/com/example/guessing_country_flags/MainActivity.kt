package com.example.guessing_country_flags

import android.content.Context
import android.content.Intent
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
//import com.example.guessing_country_flags.ui.theme.Guessing_Country_FlagsTheme
import org.json.JSONObject
import java.io.IOException

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
                                    text="COUNTRY FLAGS GAME",
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
fun HomeScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
//                navController.navigate("guessCountryGame")
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
//                navController.navigate("guessHintsGame")
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
//                navController.navigate("guessFlagGame")
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("GUESS THE FLAG")
        }

        Button(
            onClick = {
//                navController.navigate("advancedLevelGame")
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

