package com.mrfrogman.traveller2.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.views.compose.TicketTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPayView(
    navController: NavHostController,
) {
    val scrollState = rememberScrollState()
    var allAmount by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val requiredFocus = remember { FocusRequester() }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "支払いの追加") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back screen button"
                        )
                    }
                },
                actions = {
                    Button(
                        onClick = {
                            navController.navigateUp()
                        }
                    ){
                        Text(text = "作成")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TicketTextField(
                contentDescription = "Input ",
                label = "タイトル",
                value = title,
                keyboardActions = KeyboardActions(
                    onDone = {
                        requiredFocus.requestFocus()
                    }
                ),
                onValueChange = {
                    title = it
                },
            )
            TicketTextField(
                textModifier = Modifier.focusRequester(requiredFocus),
                contentDescription = "Input ",
                label = "合計金額",
                value = allAmount,
                keyboardType = KeyboardType.Number,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                onValueChange = {
                    if (it.isDigitsOnly() and (it.length < 9)) {
                        allAmount = it
                    }
                },
            )

            val memberList = listOf("hoge1", "hoge2", "hoge3", "hoge4", "hoge5")
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(memberList[0]) }
            memberList.forEach {
                PayContent(
                    allAmount = allAmount,
                    memberName = it,
                    selectedOption = selectedOption,
                    onOptionSelected = onOptionSelected
                )
            }
        }
    }
}

@Composable
fun PayContent(
    allAmount: String,
    memberName: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .selectable(
                selected = (memberName == selectedOption),
                onClick = { onOptionSelected(memberName) },
                role = Role.RadioButton
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = (memberName == selectedOption),
            onClick = null
        )
        Text(
            text = memberName,
            modifier = Modifier.padding(start = 16.dp)
        )
        TextField(
            label = {
                var amount = 0L
                if (allAmount != ""){
                    amount = allAmount.toLong() / 5
                }
                Text(text = amount.toString())
            },
            value = "",
            onValueChange = {}
        )
    }
}