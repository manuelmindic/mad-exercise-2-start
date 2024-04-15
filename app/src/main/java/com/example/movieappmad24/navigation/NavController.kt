package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen
import com.example.movieappmad24.viewmodels.MoviesViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance
    val moviesViewModel: MoviesViewModel = viewModel()
    NavHost(
        navController = navController, // pass the NavController to NavHost
        startDestination = Screen.Home.route
    ) { // pass a start destination
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController, moviesViewModel = moviesViewModel)
        }
        composable(
            Screen.Detail.passId("{movieId}"),
            arguments = listOf(navArgument(name = "movieId") {type = NavType.StringType})
        ){ backStackEntry ->
            DetailScreen(movieId = backStackEntry.arguments?.getString("movieId"), navController = navController, moviesViewModel = moviesViewModel)
        }
        composable(route = Screen.Watchlist.route){
            WatchlistScreen(navController = navController, moviesViewModel = moviesViewModel)
        }
    }
}