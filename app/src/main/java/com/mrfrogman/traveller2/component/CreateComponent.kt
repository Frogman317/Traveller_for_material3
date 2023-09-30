package com.mrfrogman.traveller2.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateComponent(){
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
    var titleInput by remember { mutableStateOf("") }
    var memberInput by remember { mutableStateOf("") }
    var memberList by remember { mutableStateOf(emptyList<String>()) }
    var errMsg by remember { mutableStateOf("") }

    Box(modifier = Modifier.padding(paddingValues)) {
        Column(Modifier.padding(12.dp)) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = {titleInput = it},
                value = titleInput,
                label = { Text("Title") },
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = "Edit pen icon") },
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                TextField(
                    modifier = Modifier.weight(1f),
                    onValueChange = {memberInput = it; errMsg=""},
                    value = memberInput,
                    label = { Text("Member") },
                    singleLine = true,
                    leadingIcon  = { Icon(Icons.Filled.Person, contentDescription = "parson icon") },
                    trailingIcon = { Icon(Icons.Filled.Add,    contentDescription = "Clear button icon",
                        modifier = Modifier.clickable {
                            if (memberInput !in memberList ){
                                memberList += memberInput
                            } else {
                                errMsg = "Cannot add people with the same name"
                            }
                        }
                    ) },
                    supportingText = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errMsg,
                            textAlign = TextAlign.Start,
                            color = Color.Red
                        )
                    },
                    isError = errMsg.isNotBlank()
                )
            }
            Column {
                for (name in memberList){
                    Member(name,memberList)
                }
            }
        }
    }
}

@Composable
fun Member(name: String, memberList: List<String>){
    Text(
        text = name,
        modifier = Modifier
            .clickable {
                Log.d("test", "Content: $memberList")
            }
    )
}

@Preview(showBackground = true, widthDp = 340)
@Composable
fun Preview() {
    CreateComponent()
}