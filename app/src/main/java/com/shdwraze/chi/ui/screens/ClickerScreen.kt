package com.shdwraze.chi.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.shdwraze.chi.R
import com.shdwraze.chi.ui.theme.CHIAndroidTestTheme

@Composable
fun ClickerApp() {
    ClickCounter(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        counterText = stringResource(id = R.string.text_view),
        buttonText = stringResource(id = R.string.text_button)
    )
}

@Composable
fun ClickCounter(
    counterText: String,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    var clickCount by rememberSaveable {
        mutableStateOf(0)
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$counterText $clickCount",
            fontSize = 24.sp
        )
        Button(onClick = { clickCount++ }) {
            Text(text = buttonText)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CHIAndroidTestTheme {
        ClickerApp()
    }
}