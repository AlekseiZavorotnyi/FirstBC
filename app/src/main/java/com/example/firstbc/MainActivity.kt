package com.example.firstbc

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import android.content.res.Configuration
import android.widget.Button
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import com.example.firstbc.ui.theme.FirstBCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen()
        }
    }
}

@Composable
fun MyScreen(
    modifier: Modifier = Modifier
) {
    val squares = rememberSaveable { mutableStateOf(emptyList<Int>()) }

    val configuration = LocalConfiguration.current
    val columnsCount = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        4
    } else {
        3
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnsCount),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 120.dp,),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(squares.value) { number ->
                val index = number - 1
                val backgroundColor = if (index % 2 == 0) Color.Red else Color.Blue
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(backgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Box №$number",
                        color = Color.White
                    )
                }
            }
        }

        Button(
            onClick = {
                val newSquares = squares.value.toMutableList()
                newSquares.add(newSquares.size + 1)
                squares.value = newSquares
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(all = 16.dp)
                .size(90.dp)
                .clip(CircleShape)
        ) {
            Text(text = "+")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreen_Preview() {
    MyScreen()
}