package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovies

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WatchlistScreen(navController: NavController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Your Watchlist!")
                    }
                )
            },
            bottomBar = {
                BottomAppBar() {
                    NavigationBarItem(
                        onClick = {navController.popBackStack()},
                        selected = true,
                        label = { Text (text = "Home") },
                        icon = {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Home",)
                        }
                    )
                    NavigationBarItem(
                        onClick = {},
                        selected = true,
                        label = { Text (text = "Watchlist") },
                        icon = {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = "Watchlist",)
                        }
                    )
                }
            },
            content = {MovieList(getMovies(), navController)}
        )
    }
}