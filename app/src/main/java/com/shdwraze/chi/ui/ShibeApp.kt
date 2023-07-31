@file:OptIn(ExperimentalMaterial3Api::class)

package com.shdwraze.chi.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shdwraze.chi.ui.screens.HomeScreen
import com.shdwraze.chi.ui.screens.ShibeViewModel

@Composable
fun ShibeApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ShibeTopAppBar(scrollBehavior = scrollBehavior)
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val shibeViewModel: ShibeViewModel = viewModel(factory = ShibeViewModel.Factory)
            HomeScreen(
                shibeUiState = shibeViewModel.shibeUiState,
                retryAction = shibeViewModel::getShibePhotos
            )
        }
    }
}

@Composable
fun ShibeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Shibe",
                style = MaterialTheme.typography.headlineSmall
            )
        })
}