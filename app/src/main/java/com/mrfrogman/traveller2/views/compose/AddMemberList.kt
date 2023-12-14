package com.mrfrogman.traveller2.views.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrfrogman.traveller2.R
@Stable
@Composable
fun AddMemberList(
    member: String = "Default Name",
) {
    val shadowColor = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray
    var showFlg by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(68.dp)
            .width(348.dp),
        contentAlignment = Alignment.CenterStart
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .offset(2.dp, 2.dp),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.member_ticket),
            contentDescription = "Member's name background",
            colorFilter = ColorFilter.tint(shadowColor)
        )
        Image(
            modifier = Modifier
                .width(280.dp)
                .height(68.dp),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.member_ticket),
            contentDescription = "Member's name background",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer)
        )
        Text(
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentHeight(),
            text = member,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(MaterialTheme.colorScheme.primary)
                .width(68.dp)
                .height(68.dp),
            onClick = {
                showFlg = true
            }
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More menu icon button",
                tint = MaterialTheme.colorScheme.onPrimary
            )
            DropdownMenu(
                expanded = showFlg,
                onDismissRequest = { showFlg = false },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Name edit icon"
                        )
                    },
                    text = { Text(text = "名前の変更") },
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(16.dp)
                )
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Member delete icon"
                        )
                    },
                    text = { Text(text = "削除") },
                    onClick = { /*TODO*/ }
                )
            }
        }
    }
}