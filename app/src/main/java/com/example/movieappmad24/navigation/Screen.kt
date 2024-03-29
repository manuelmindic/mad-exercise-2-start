package com.example.movieappmad24.navigation

sealed class Screen(val route: String){
    object Home : Screen(route = "homescreen")
    object Detail : Screen(route = "detailscreen"){
        fun passId(id : String): String{
            return "detailScreen/$id"
        }
    }
    object Watchlist : Screen(route = "watchlistscreen")
}