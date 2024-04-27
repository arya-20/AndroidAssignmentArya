package com.example.bottomnav1.presentation.navigation

import BulkPrepScreen
import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnav1.R
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.contact1.Contact
import com.example.bottomnav1.presentation.screens.add.AddScreen
import com.example.bottomnav1.presentation.screens.edit.EditScreen
import com.example.bottomnav1.presentation.screens.login.LoginScreen
import com.example.bottomnav1.presentation.screens.signup.SignUpScreen
import kotlin.system.exitProcess

sealed class NavScreen(var icon:Int, var route:String){
    data object Home: NavScreen(R.drawable.home, "Home")
    data object Add: NavScreen(R.drawable.add, "Add")
    data object Edit: NavScreen(R.drawable.add, "Edit")//drawable is not relevant
    data object Exit: NavScreen(R.drawable.logout, "Logout")
    data object Login: NavScreen(R.drawable.home, "Login")
    data object SignUp: NavScreen(R.drawable.home, "SignUp")
    data object BulkPrep: NavScreen(R.drawable.home, "BulkPrep")


}

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    var selectedContact: Contact? =null
    NavHost(navController,
        startDestination = NavScreen.Login.route) {

        composable(NavScreen.Login.route) {
            LoginScreen(
                navigateToSignUpScreen = {
                    navController.navigate(NavScreen.SignUp.route)
                },
                navigateToHomeScreen = {
                    navController.navigate(NavScreen.Home.route)
                }
            )
        }
        composable(NavScreen.SignUp.route) {
            SignUpScreen(
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(NavScreen.Home.route) {
            HomeScreen(
                navController = navController,
                onIndexChange = {
                    selectedContact = it
                },
                onClickToBulkPrep = {
                    navController.navigate(NavScreen.BulkPrep.route)
                }
            )
        }
        composable(NavScreen.BulkPrep.route) {
            BulkPrepScreen(
                navController = navController
            ) {
                navController.popBackStack()
            }
        }


        composable(NavScreen.Add.route) {
            AddScreen(navController = navController,
                        onClickToHome ={ navController.popBackStack()})
        }
        composable(NavScreen.Edit.route) {
            EditScreen(navController = navController,
                        selectedContact=selectedContact!!,
                        onClickToHome = {
                            if(selectedContact!=null) {
                                navController.navigate("home")
                            }
                        })
        }
        composable(NavScreen.Exit.route) {
            ContactApplication.container.authRepository.signOut()
            exitProcess(0)
        }
    }
}
