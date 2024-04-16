package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.movieappmad24.SimpleTopAppBar
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.viewmodels.MoviesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movieId : String?, navController: NavController, moviesViewModel: MoviesViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                SimpleTopAppBar(content = "detail", title = movieId!!, navController = navController)
            }
        ){ innerPadding ->
            Column {
                MovieRow(modifier = Modifier.padding(innerPadding), movie = getMovie(movieId)!!, onFavoriteClick = { moviesViewModel.toggleFavoriteMovie(movieId!!) })
                LazyRow {
                    items(getMovie(movieId)!!.images) {
                            image -> AsyncImage(
                        model = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )
                    }
                }
            }
        }
    }
}