package com.shdwraze.chi.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shdwraze.chi.R

@Composable
fun HomeScreen(
    shibeUiState: ShibeUiState,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
) {
    when (shibeUiState) {
        is ShibeUiState.Success -> PhotoGrid(photos = shibeUiState.photos)
        is ShibeUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is ShibeUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize(), retryAction)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGrid(photos: List<String>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(dimensionResource(id = R.dimen.grid_cell)),
        verticalItemSpacing = dimensionResource(id = R.dimen.spacing),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing)),
        modifier = Modifier.fillMaxSize()
    ) {
        items(photos) { photo ->
            ShibePhotoCard(photo = photo)
        }
    }
}

@Composable
fun ShibePhotoCard(
    photo: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(photo)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(R.string.shiba_inu),
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img)
    )
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = { retryAction() }) {
            Text(stringResource(id = R.string.retry))
        }
    }
}