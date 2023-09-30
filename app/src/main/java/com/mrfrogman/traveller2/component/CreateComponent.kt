package com.mrfrogman.traveller2.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

var text = ""
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateComposeComponent(){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = "Add Plan",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                ) },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) { Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Settings Button"
                    )
                    }
                })
        },
        content = {
            Content(it)
        }
    )
}
@Composable
fun Content(paddingValues: PaddingValues){

}

@Preview(showBackground = true, widthDp = 340)
@Composable
fun Preview() {
    CreateComposeComponent()
}