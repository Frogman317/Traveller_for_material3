package com.mrfrogman.traveller2.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrfrogman.traveller2.R

@Composable
fun TitleView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val modifier = Modifier
            .width(200.dp)
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )
        Button(
            modifier = modifier,
            onClick = {
            /*TODO*/
        }) {
            Text(
                text = "ログイン"
            )
        }
        Button(
            modifier = modifier,
            onClick = {
            /*TODO*/
        }) {
            Text(
                text = "新規登録"
            )
        }
        Button(
            modifier = modifier,
            onClick = {
            /*TODO*/
        }) {
            Text(
                text = "ゲストログイン"
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 720)
@Composable
fun TitlePreview() {
    TitleView()
}