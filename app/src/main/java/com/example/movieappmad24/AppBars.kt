package com.example.movieappmad24

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovie
import com.example.movieappmad24.navigation.Screen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleTopAppBar(content : String, title : String, navController : NavController){
    if (content.equals("detail")){
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(getMovie(title)!!.title)
            },
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}){
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back")
                }
            }
        )
    }else{
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(title)
            }
        )
    }
}

@Composable
fun SimpleBottomAppBar(navController : NavController){
    BottomAppBar() {
        NavigationBarItem(
            onClick = {
                navController.navigate(route = Screen.Home.route){
                    popUpTo(Screen.Home.route){
                        inclusive = true
                    }
                }
                      },
            selected = true,
            label = { Text (text = "Home") },
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home",)
            }
        )
        NavigationBarItem(
            onClick = { navController.navigate(route = Screen.Watchlist.route){
                popUpTo(Screen.Watchlist.route){
                    inclusive = true
                }
            }
                      },
            selected = true,
            label = { Text (text = "Watchlist") },
            icon = {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Watchlist",)
            }
        )
    }

}