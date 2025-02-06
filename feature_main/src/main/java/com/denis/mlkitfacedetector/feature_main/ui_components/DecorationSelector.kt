package com.denis.mlkitfacedetector.feature_main.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.denis.mlkitfacedetector.feature_main.data_class.FaceDecoration

@Composable
fun DecorationSelector(
    selectedDecoration: FaceDecoration,
    onDecorationSelected: (FaceDecoration) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        items(FaceDecoration.entries) { decoration ->
            Image(
                painter = painterResource(id = decoration.drawableRes),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = if (decoration == selectedDecoration) Color.Gray else Color.Transparent)
                    .clickable { onDecorationSelected(decoration) }
            )
        }
    }
}