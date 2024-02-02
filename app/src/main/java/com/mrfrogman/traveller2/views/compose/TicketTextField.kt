package com.mrfrogman.traveller2.views.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mrfrogman.traveller2.R

@Composable
fun TicketTextField(
    modifier: Modifier = Modifier,
    contentDescription: String,
    label: String,
    supportingText: String = "",
    textStyle: TextStyle = LocalTextStyle.current,
    value: String,
    suffix: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions(onDone = {}),
    onValueChange: (String) -> Unit,
    textModifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(top = 8.dp)
            .width(400.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Image(
            modifier = Modifier
                .padding(top = 2.dp, start = 3.dp)
                .height(80.dp)
                .fillMaxWidth(0.9f),
            painter = painterResource(id = R.drawable.tickettextfield),
            contentDescription = "content shadow",
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(
                color = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray
            )
        )
        Image(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(0.9f),
            painter = painterResource(id = R.drawable.tickettextfield),
            contentDescription = contentDescription,
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(color = colorScheme.primaryContainer)
        )
        OutlinedTextField(
            modifier = textModifier
                .padding(top = 4.dp)
                .fillMaxWidth(0.8f),
            label = {
                Text(text = label)
            },
            textStyle = textStyle,
            value = value,
            suffix = { Text(text = suffix) },
            onValueChange = onValueChange,
            isError = supportingText != "",
            maxLines = 1,
            supportingText = { Text(
                modifier = Modifier.padding(top = 8.dp),
                text = supportingText,
                color = colorScheme.error,
            ) },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            keyboardActions = keyboardActions,
            trailingIcon = {
                IconButton(onClick = {
                    onValueChange("")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = "Type clear button"
                    )
                }
            }
        )
    }
}