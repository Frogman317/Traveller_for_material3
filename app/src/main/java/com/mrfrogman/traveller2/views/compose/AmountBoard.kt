package com.mrfrogman.traveller2.views.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrfrogman.traveller2.R

@Composable
fun AmountBoard(
    amount: String = "0",
    themeGray: Color
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .padding(top = 4.dp, start = 4.dp)
                .width(360.dp)
                .height(160.dp),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ticketamountboard),
            contentDescription = "content shadow",
            colorFilter = ColorFilter.tint(themeGray)
        )
        Row(
            modifier = Modifier.width(360.dp)
        ) {
            Image(
                modifier = Modifier
                    .height(160.dp)
                    .weight(2.4f),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.ticketamountboard1),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer)
            )
            Image(
                modifier = Modifier
                    .height(160.dp)
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        Log.d("TAG", "HomeView: ")
                    },
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.ticketamountboard2),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(252.dp)
                    .height(160.dp)
                    .padding(16.dp)
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "現在の合計金額",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = amount+"円",
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Text(
                modifier = Modifier.width(105.dp),
                textAlign = TextAlign.Center,
                text = "決済",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp
            )
        }
    }
}
