package com.mrfrogman.traveller2.component

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.R
import com.mrfrogman.traveller2.database.DatabaseHelper
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateComponent(navController: NavHostController){

    var titleInput by rememberSaveable { mutableStateOf("") }
    var memberInput by rememberSaveable { mutableStateOf("") }
    var memberList by rememberSaveable { mutableStateOf(emptyList<String>()) }

    var titleInputErrorMessage by remember { mutableStateOf("") }
    var memberInputErrorMessage by remember { mutableStateOf("") }
    val sameNameError = stringResource(id = R.string.same_name_error)
    val emptyNameError = stringResource(id = R.string.empty_name_error)
    val emptyMemberError = stringResource(id = R.string.empty_member_error)
    val emptyTitleError = stringResource(id = R.string.empty_title_error)

    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = stringResource(id = R.string.Create_page_title),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                ) },
                actions = {
                    IconButton(onClick = {
                        if ( titleInput != "" ){
                            if (  memberList.isNotEmpty() ){
                                val values = ContentValues().apply {
                                    put("title",titleInput)
                                    put("detail","")
                                    put("member",memberList.size)
                                    put("amount",0)
                                    put("date", LocalDateTime.now().toString())
                                }
                                val helper = DatabaseHelper(context)
                                val db: SQLiteDatabase = helper.writableDatabase
                                val planId = db.insert("plans", null, values)
                                for (value in memberList) {
                                    val arrayValuesContent = ContentValues().apply {
                                        put("name", value)
                                        put("plan_id", planId)
                                    }
                                    db.insert("member", null, arrayValuesContent)
                                }
                                navController.navigate("main")
                            }else{
                                memberInputErrorMessage = emptyMemberError
                            }
                        }else{
                            titleInputErrorMessage = emptyTitleError
                        }

                    }) { Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Settings Button"
                    )}
                })
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Column(Modifier.padding(12.dp)) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = {titleInput = it; titleInputErrorMessage=""},
                        value = titleInput,
                        label = { Text(stringResource(id = R.string.plan_title)) },
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = "Edit pen icon") },
                        supportingText = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = titleInputErrorMessage,
                                textAlign = TextAlign.Start,
                                color = Color.Red
                            )
                        },
                        isError = titleInputErrorMessage.isNotBlank()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = {memberInput = it; memberInputErrorMessage=""},
                        value = memberInput,
                        label = { Text(stringResource(id = R.string.member_name)) },
                        singleLine = true,
                        leadingIcon  = { Icon(Icons.Filled.Person, contentDescription = "parson icon") },
                        trailingIcon = { Icon(Icons.Filled.Add,    contentDescription = "Clear button icon",
                            modifier = Modifier.clip(CircleShape).clickable {
                                if ( memberInput.isNotEmpty() ){
                                    if ( memberInput !in memberList ){
                                        memberList += memberInput
                                        memberInput = ""
                                    } else {
                                        memberInputErrorMessage = sameNameError
                                    }
                                } else {
                                    memberInputErrorMessage = emptyNameError
                                }
                            }
                        ) },
                        supportingText = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = memberInputErrorMessage,
                                textAlign = TextAlign.Start,
                                color = Color.Red
                            )
                        },
                        isError = memberInputErrorMessage.isNotBlank()
                    )
                    LazyColumn {
                        items(memberList) { name ->
                            var expanded by remember { mutableStateOf(false) }
                            var isEditing by remember { mutableStateOf(false)}
                            var newName by remember { mutableStateOf("")}
                            val focusRequester = remember { FocusRequester() }
                            var itemErrorMessage by remember { mutableStateOf("") }
                            if ( isEditing ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                ){
                                    OutlinedTextField(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .focusRequester(focusRequester),
                                        value = newName,
                                        label = { Text(text = name) },

                                        onValueChange = { newName = it; itemErrorMessage="" },
                                        trailingIcon = { Icon(Icons.Filled.Check, contentDescription = "Edit TextField",
                                            modifier = Modifier.clip(CircleShape).clickable{
                                                if ( newName.isNotEmpty() ){
                                                    if ( newName !in memberList ){
                                                        memberList = memberList.map { if (it == name) newName else it }
                                                        newName = ""
                                                        isEditing = false
                                                    } else {
                                                        itemErrorMessage = sameNameError
                                                    }
                                                } else {
                                                    itemErrorMessage = emptyNameError
                                                }
                                            }
                                        )},
                                        supportingText = {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = itemErrorMessage,
                                                textAlign = TextAlign.Start,
                                                color = Color.Red
                                            )
                                        },
                                        isError = itemErrorMessage.isNotBlank()
                                    )
                                    LaunchedEffect(Unit) {
                                        focusRequester.requestFocus()
                                    }
                                }

                            }else{
                                ListItem(
                                    headlineContent = { Text(text = name) },
                                    trailingContent = {
                                        Icon(Icons.Filled.MoreVert, contentDescription = "",
                                            modifier = Modifier.clip(CircleShape).clickable {
                                                expanded = true
                                            }
                                        )
                                        DropdownMenu(
                                            expanded = expanded,
                                            onDismissRequest = { expanded = false })
                                        {
                                            DropdownMenuItem(
                                                text = { Text(stringResource(id = R.string.edit_name)) },
                                                onClick = { isEditing = true; expanded = false },
                                                leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) }
                                            )
                                            DropdownMenuItem(
                                                text = { Text(stringResource(id = R.string.delete)) },
                                                onClick = { memberList = memberList.filterNot { it == name }; expanded = false },
                                                leadingIcon = { Icon(Icons.Outlined.Delete, contentDescription = null) }
                                            )
                                        }
                                    }
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    )
}