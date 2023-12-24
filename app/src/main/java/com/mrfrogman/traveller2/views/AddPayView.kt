package com.mrfrogman.traveller2.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.views.compose.TicketTextField
import java.lang.Integer.MAX_VALUE

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

            val memberList by remember { mutableStateOf(mutableStateListOf<String>("name1", "name2", "name3", "name4", "name5")) }
            val amountList by remember { mutableStateOf(mutableStateListOf<String>("","","","","")) }
            val isPaidList by remember { mutableStateOf(mutableStateListOf<Boolean>(false,false,false,false,false)) }
            var paidMember by remember { mutableStateOf(-1) }
            var isExpanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = {
                    isExpanded = it
                }
            ) {
                var dropdownText = ""
                if (paidMember > -1){
                    dropdownText = memberList[paidMember]
                }
                TextField(
                    label = { Text(text = "支払い者")},
                    value = dropdownText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(text = "選択してください")
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(0.8f)
                )
                

                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = {
                        isExpanded = false
                    }
                ) {
                    for (index in memberList.indices){
                        DropdownMenuItem(
                            text = {
                                Text(text = memberList[index])
                            },
                            onClick = {
                                isPaidList[index] = true
                                paidMember = index
                                isExpanded = false
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.Bottom
            )  {
                Text(
                    text = "支払い可否",
                    modifier = Modifier.width(60.dp)
                )
                Text(
                    text = "名前",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "金額",
                    modifier = Modifier.width(120.dp)
                )
            }

            HorizontalDivider()

            for (index in memberList.indices){
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if ((amountList[index] != "" ) or (paidMember == index)) isPaidList[index] = true
                    Checkbox(
                        checked = isPaidList[index],
                        onCheckedChange = {
                            isPaidList[index] = it
                            if (!it){
                                amountList[index] = ""
                            }
                        }
                    )
                    Text(
                        text = memberList[index],
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    )
                    var amount = 0
                    val isPaidCount = isPaidList.count { it }
                    val amountCount = amountList.count { it != "" }
                    val unspecified = isPaidCount - amountCount
                    if (unspecified > 0){
                        if (true){
                            
                        }
                    }
                    TextField(
                        modifier = Modifier
                            .width(120.dp),
                        placeholder = {
                            Text(text = amount.toString())
                        },
                        value = amountList[index],
                        maxLines = 1,
                        onValueChange = {
                            if (it.isDigitsOnly()){
                                amountList[index] = it
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )
                }
            }
        }
    }
}