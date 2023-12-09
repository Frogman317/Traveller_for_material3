package com.mrfrogman.traveller2.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavHostController) {
    val listState = rememberLazyListState()
    var segmentIndex by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "記録") },//TODO 仮タイトル
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back screen button"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    Log.d("TAG", "HomeView: ")
                },
                icon = { Icon(Icons.Filled.Add, "Localized Description") },
                text = { Text(text = "Extended FAB") },
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            state = listState,
        ) {
            item {
                Spacer(modifier = Modifier.padding(8.dp))
                MainAmountBoard()
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FilledTonalButton(
                        onClick = {

                        }) {
                        Text(text = "詳細")
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(
                        onClick = {

                        }) {
                        Text(text = "メンバーの追加")
                    }
                }
                val options = listOf("すべての表示", "自分の表示")
                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .background(colorScheme.background)
                        .padding(20.dp)
                        .fillMaxWidth(),
                ) {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = options.size
                            ),
                            onClick = { segmentIndex = index },
                            selected = index == segmentIndex
                        ) {
                            Text(label)
                        }
                    }
                }
            }
            items(
                count = 20,
                key = { it }
            ) { index ->
                ListContent(index = index)
            }
        }
    }
}

@Composable
fun MainAmountBoard() {
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
            colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray)
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
                colorFilter = ColorFilter.tint(colorScheme.primaryContainer)
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
                colorFilter = ColorFilter.tint(colorScheme.primary)
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
                        color = colorScheme.primary,
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
                        text = "1,000,000円",
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
                color = colorScheme.onPrimary,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ListContent(index: Int) {
    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
        text = "12/9"
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                //TODO
            }
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Item $index",
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "1,000"+"円",
            fontWeight = FontWeight.Medium,
        )
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = "Navigate Button for detail of list"
        )
    }
}