package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun HomeScreen(navController : NavController) {
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
                        Text("Manus Movie App")
                    }
                )
            },
            bottomBar = {
                BottomAppBar() {
                    NavigationBarItem(
                        onClick = {},
                        selected = true,
                        label = { Text (text = "Home") },
                        icon = {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Home",)
                        }
                    )
                    NavigationBarItem(
                        onClick = {navController.navigate("watchlistscreen")},
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

@Composable
fun MovieList(movieList:List<Movie> = getMovies(), navController: NavController){
    LazyColumn{
        items(movieList) {mvl -> MovieRow(movie = mvl, onItemClick = {id -> navController.navigate("detailScreen/$id")})}
    }
}

// Method partially from class in MAD from Leon
@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit ={} ){
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { onItemClick(movie.id) },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = "Movie Image",
                    modifier = Modifier.size(400.dp),
                    contentScale = ContentScale.Crop)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd)
                { Icon(
                    tint = MaterialTheme.colorScheme.secondary,
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Add Favorites")
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = movie.title,style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                )
                IconButton(onClick = {expanded = !expanded}){
                    Icon(modifier = Modifier,
                        imageVector = if (!expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown , contentDescription = "Arrow Up",
                    )
                }
            }
            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    if (expanded) {
                        Text(text = "Director: ${movie.director}")
                        Text(text = "Released: ${movie.year}")
                        Text(text = "Genre: ${movie.genre}")
                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Rating: ${movie.rating}")
                        Text(text = "----------------------------------------------")
                        Text(text = "Plot: ${movie.plot}")
                    }
                }
            }
        }
    }
}