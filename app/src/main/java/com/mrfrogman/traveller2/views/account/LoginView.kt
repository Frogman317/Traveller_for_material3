package com.mrfrogman.traveller2.views.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.R

@Composable
fun LoginView(
    navController: NavHostController,
) {
    var a by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TicketTextField(
            contentDescription = "",
            label = "メールアドレス",
            value = a,
        ){
            a = it
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketTextField(
    contentDescription: String,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(0.9f),
            painter = painterResource(id = R.drawable.tickettextfield),
            contentDescription = contentDescription,
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(color = colorScheme.primary)
        )
        OutlinedTextField(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(0.8f),
            label = {
                Text(text = label)
            },
            value = value,
            onValueChange = onValueChange,
            //TODO 色の指定全部指定しないといけないっぽい
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = colorScheme.onPrimary
            )
        )
    }
}