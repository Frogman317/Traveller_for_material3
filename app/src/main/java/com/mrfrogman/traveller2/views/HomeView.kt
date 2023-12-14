package com.mrfrogman.traveller2.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.views.compose.AmountBoard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavHostController) {
    val listState = rememberLazyListState()
    var segmentIndex by remember { mutableIntStateOf(0) }
    val themeGray = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray
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
                modifier = Modifier.offset(

                ),
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
            contentPadding = PaddingValues(vertical = 8.dp),
            state = listState,
        ) {
            item {
                AmountBoard(themeGray)
            }
            item {
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FilledTonalButton(
                        modifier = Modifier.width(150.dp),
                        onClick = {

                        }) {
                        Text(text = "詳細")
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(
                        modifier = Modifier.width(150.dp),
                        onClick = {

                        }) {
                        Text(text = "メンバーの追加")
                    }
                }
            }
            item {
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
//            for (i in 0..14){
//                item {
//                    ListContent(data = "test datum")
//                }
//            }
            items(15){
                ListContent(data = "test datum")
            }
        }
    }
}

@Stable
@Composable
fun ListContent(
    data: String
) {
    val themeGray = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray
    Text(
        modifier = Modifier.padding(start = 16.dp),
        color = themeGray,
        text = "12/9"
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                //TODO
            }
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Item $data",
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "1,000"+"円",
            fontWeight = FontWeight.Medium,
        )
        Icon(
            modifier = Modifier.padding(end = 12.dp),
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = "Navigate Button for detail of list",
            tint = themeGray
        )
    }
}