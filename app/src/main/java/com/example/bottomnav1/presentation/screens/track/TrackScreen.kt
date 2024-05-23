package com.example.bottomnav1.presentation.screens.track

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bottomnav1.presentation.components.BottomNavBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrackScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var currentWeight by remember { mutableStateOf("") }
    var targetWeight by remember { mutableStateOf("") }
    var isKg by remember { mutableStateOf(true) }
    var weightDifference by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Track Progress",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(16.dp))

            TrackEntryField(
                label = "Current Weight",
                text = currentWeight,
                onValueChange = { currentWeight = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            TrackEntryField(
                label = "Target Weight",
                text = targetWeight,
                onValueChange = { targetWeight = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isKg) "kg" else "lb",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Switch(
                    checked = isKg,
                    onCheckedChange = { isKg = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White,
                        checkedTrackColor = Color.Gray,
                        uncheckedTrackColor = Color.Gray
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val currentWeightValue = currentWeight.toFloatOrNull() ?: 0f
                    val targetWeightValue = targetWeight.toFloatOrNull() ?: 0f
                    val difference = targetWeightValue - currentWeightValue
                    val unit = if (isKg) "kg" else "lb"
                    weightDifference = "Weight to go: $difference $unit"
                    keyboardController?.hide()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Track")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = weightDifference,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackEntryField(
    label: String,
    text: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.White) },
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
        keyboardOptions = keyboardOptions,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.White, RoundedCornerShape(26.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color.White,
        ),
        shape = RoundedCornerShape(26.dp)
    )
}
