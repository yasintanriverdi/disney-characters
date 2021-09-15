package com.yasintanriverdi.disneycharacters.presentation.ui.layout

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yasintanriverdi.disneycharacters.R

@Composable
fun RowSpacer(value: Int) = Spacer(modifier = Modifier.width(value.dp))

@Composable
fun ColumnSpacer(value: Int) = Spacer(modifier = Modifier.height(value.dp))

@Composable
fun ErrorView(
    @StringRes messageRes: Int = R.string.character_list_default_error_message,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Button(
            onClick = { onItemClick() }, colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Red
            )
        ) {
            Text(stringResource(messageRes))
        }
    }
}
