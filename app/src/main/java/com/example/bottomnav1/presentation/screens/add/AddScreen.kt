package com.example.bottomnav1.presentation.screens.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottomnav1.R
import com.example.bottomnav1.data.recipe1.Category
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.components.CustomButton
import com.example.bottomnav1.presentation.components.CustomTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen(vm: AddViewModel = viewModel(factory = AddViewModel.Factory),
              modifier: Modifier = Modifier,
              navController: NavHostController,
              onClickToHome: () -> Unit
              ) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            androidx.compose.material.Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.add),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Column {

                CustomTextField(
                    stringResource(R.string.name),
                    text = vm.name,
                    onValueChange = { vm.name = it },
                    errorMessage = stringResource(R.string.name_error),
                    !vm.isNameValid()
                )

                RoleSelectionDropdown(
                    selectedCategory = vm.category ?: Category.BULK,
                    onCategorySelected = { category -> vm.category = category },
                    categoryisValid = vm.isCategoryValid()
                )

                CustomTextField(
                    stringResource(R.string.ingredients),
                    text = vm.ingredients,
                    onValueChange = { vm.ingredients = it },
                    errorMessage = stringResource(R.string.name_error),
                    !vm.isIngredientsValid()
                )

                CustomTextField(
                    stringResource(R.string.instructions),
                    text = vm.instructions,
                    onValueChange = { vm.instructions = it },
                    errorMessage = stringResource(R.string.name_error),
                    !vm.isInstructionsValid()
                )

                CustomButton(
                    stringResource(R.string.add),
                    clickButton = {
                        vm.addContact()
                        keyboardController?.hide()
                        onClickToHome()
                    })
            }
        }
    }
}


@Composable
fun RoleSelectionDropdown(
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit,
    categoryisValid: Boolean
) {

    var expanded by remember { mutableStateOf(true) } // Track dropdown state

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        OutlinedTextField( // Use OutlinedTextField for dropdown appearance
            value = if (selectedCategory == Category.NONE) stringResource(R.string.select_category) else selectedCategory.name, // Set initial value to selected role name or placeholder // Set initial value to selected role name
            onValueChange = { }, // Disable text editing (optional)
            readOnly = true, // Make the field read-only (optional)
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.select_category)) }, // Set label
            trailingIcon = { // Add dropdown icon
                Icon(
                    painter = painterResource(id = R.drawable.dropdown), // Replace with your icon drawable
                    contentDescription = null
                )
            }
        )
        if (expanded) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.LightGray// Set the background color here
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth(),
                    offset = DpOffset(-30.dp, 0.dp)
                ) {
                    val category = Category.values()
                    category.forEach { category ->
                        DropdownMenuItem(
                            onClick = {
                                onCategorySelected(category)
                                expanded = false
                            }
                        ) {
                            Text(text = category.name)
                        }
                    }
                }
            }
        }
    }
}