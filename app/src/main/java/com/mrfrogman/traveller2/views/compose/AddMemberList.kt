package com.mrfrogman.traveller2.views.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddMemberList(
    member: String = "Default Name",
    deleteMember: (String) -> Unit
) {
    var showFlg by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            modifier = Modifier
                .padding(start = 24.dp)
                .weight(1f),
            text = member,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        IconButton(
            onClick = {
                showFlg = true
            }
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More menu icon button"
            )
            DropdownMenu(
                expanded = showFlg,
                onDismissRequest = { showFlg = false },
            ) {
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Name edit icon"
                        )
                    },
                    text = { Text(text = "名前の変更") },
                    onClick = { /*TODO*/ }
                )
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Member delete icon"
                        )
                    },
                    text = { Text(text = "削除") },
                    onClick = { deleteMember(member) }
                )
            }
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}