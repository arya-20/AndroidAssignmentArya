package com.example.bottomnav1.presentation.screens.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.components.CustomButton
import com.example.bottomnav1.presentation.components.CustomTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen(
    vm: EditViewModel = viewModel(factory = EditViewModel.Factory),
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedRecipe: Recipe,
    onClickToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        vm.setSelectedContact(selectedRecipe)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.edit),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Column {
                CustomTextField(
                    stringResource(R.string.name),
                    text = vm.name,
                    onValueChange = { vm.name = it },
                    errorMessage = stringResource(R.string.name_error),
                    errorPresent = !vm.nameIsValid()
                )

//                RoleSelectionDropdown(
//                    selectedCategory = vm.category ?: Category.NONE,
//                    onCategorySelected = { category -> vm.category = category },
//                    categoryIsValid = vm.isCategoryValid()
//                )

                CustomTextField(
                    stringResource(R.string.ingredients),
                    text = vm.ingredients,
                    onValueChange = { vm.ingredients = it },
                    errorMessage = stringResource(R.string.ingredients_error),
                    errorPresent = !vm.ingredientsIsValid()
                )

                CustomTextField(
                    stringResource(R.string.instructions),
                    text = vm.instructions,
                    onValueChange = { vm.instructions = it },
                    errorMessage = stringResource(R.string.instructions_error),
                    errorPresent = !vm.instructionsIsValid()
                )

                CustomButton(
                    stringResource(R.string.edit),
                    clickButton = {
                        vm.updateContact()
                        onClickToHome()
                    }
                )
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

    var expanded by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        OutlinedTextField(
            value = if (selectedCategory == Category.NONE) stringResource(R.string.select_category) else selectedCategory.name,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.select_category)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.dropdown),
                    contentDescription = null
                )
            }
        )
        if (expanded) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray
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