package com.example.guessing_country_flags

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryFlagsApp()
        }
    }
}

@Composable
fun CountryFlagsApp() {
    var selectedCategory by remember { mutableStateOf("Guess the Country") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { selectedCategory = "Guess the Country" },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Guess the Country")
        }

        Button(
            onClick = { selectedCategory = "Guess-Hints" },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Guess-Hints")
        }

        Button(
            onClick = { selectedCategory = "Guess the Flag" },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Guess the Flag")
        }

        Button(
            onClick = { selectedCategory = "Advanced Level" },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Advanced Level")
        }

        when (selectedCategory) {
            "Guess the Country" -> GuessTheCountryScreen()
            "Guess-Hints" -> GuessHintsScreen()
            "Guess the Flag" -> GuessTheFlagScreen()
            "Advanced Level" -> AdvancedLevelScreen()
        }
    }
}

@Composable
fun GuessTheCountryScreen() {
    // Implement the Guess the Country screen
}

@Composable
fun GuessHintsScreen() {
    // Implement the Guess-Hints screen
}

@Composable
fun GuessTheFlagScreen() {
    // Implement the Guess the Flag screen
}

@Composable
fun AdvancedLevelScreen() {
    // Implement the Advanced Level screen
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CountryFlagsApp()
}



