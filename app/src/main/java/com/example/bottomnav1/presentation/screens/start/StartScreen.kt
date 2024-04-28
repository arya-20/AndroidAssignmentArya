package com.example.bottomnav1.presentation.screens.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottomnav1.R
import com.example.bottomnav1.data.contact1.Contact
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.components.CustomButton
import com.example.bottomnav1.presentation.screens.home.components.LazyColumnWithSelection
import com.example.bottomnav1.presentation.utils.Util.Companion.showMessage

@SuppressLint("StateFlowValueCalledInComposition",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun HomeScreen(vm: StartViewModel = viewModel(factory = HomeViewModel.Factory),
               modifier: Modifier = Modifier,
               onIndexChange: (Contact?) -> Unit,//function to change the selected contact
               onClickToEdit: () -> Unit,
               navController: NavHostController) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            androidx.compose.material.Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Home",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            val userState by vm.userState.collectAsState()

            if (userState.data.isNotEmpty()) //Some data to display
                LazyColumnWithSelection(vm, onIndexChange)

            if(vm.userState.value.errorMessage.isNotBlank()){ //Problem retrieving data
                showMessage(context,vm.userState.value.errorMessage)
            }

            CustomButton(stringResource(R.string.edit), onClickToEdit)

            CustomButton(stringResource(R.string.delete)) {
                if (!vm.contactHasBeenSelected()) {
                    Toast.makeText(context, "You need to select a contact", Toast.LENGTH_LONG)
                        .show()
                } else {
                    vm.deleteContact()
                    onIndexChange(null)
                }
            }

        }
    }
}