package com.yasintanriverdi.disneycharacters.presentation.character_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.yasintanriverdi.disneycharacters.presentation.ui.layout.ColumnSpacer

@Composable
fun CharacterTags(
    title: String,
    tags: List<String>?
) {
    if (tags.isNullOrEmpty()) return

    ColumnSpacer(16)

    Text(
        text = title,
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    ColumnSpacer(8)

    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
        modifier = Modifier.fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        tags.forEach { film ->
            CharacterTag(tag = film)
        }
    }


}