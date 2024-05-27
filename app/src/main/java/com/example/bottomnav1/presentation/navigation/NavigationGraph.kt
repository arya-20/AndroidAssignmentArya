package com.example.bottomnav1.presentation.navigation

import HomeScreen
import SettingsScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnav1.R
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.auth.AuthRepository
import com.example.bottomnav1.data.contact1.Contact
import com.example.bottomnav1.data.contact1.ContactDAO
import com.example.bottomnav1.data.contact1.ContactRepository
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.presentation.screens.add.AddScreen
import com.example.bottomnav1.presentation.screens.bulk.BulkPrepScreen
import com.example.bottomnav1.presentation.screens.edit.EditScreen
import com.example.bottomnav1.presentation.screens.edit.EditViewModel
import com.example.bottomnav1.presentation.screens.login.LoginScreen
import com.example.bottomnav1.presentation.screens.recipe.RecipeDetailViewModel
import com.example.bottomnav1.presentation.screens.recipe.RecipeDetailsScreen
import com.example.bottomnav1.presentation.screens.signup.SignUpScreen
import com.example.bottomnav1.presentation.screens.start.StartScreen
import com.example.bottomnav1.presentation.screens.track.TrackScreen
import com.example.bottomnav1.presentation.screens.vegan.VeganScreen
import com.example.bottomnav1.presentation.screens.weightGain.WeightGainScreen
import com.example.bottomnav1.presentation.screens.weightLoss.WeightLossScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlin.system.exitProcess


sealed class NavScreen(var icon:Int, var route:String){
    data object Start: NavScreen(R.drawable.home, "Start")
    data object Home: NavScreen(R.drawable.home, "Home")
    data object Add: NavScreen(R.drawable.add, "Add")
    data object Edit: NavScreen(R.drawable.edit, "Edit")
    data object Exit: NavScreen(R.drawable.logout, "Logout")
    data object Login: NavScreen(R.drawable.home, "Login")
    data object SignUp: NavScreen(R.drawable.home, "SignUp")
    data object BulkPrep: NavScreen(R.drawable.home, "BulkPrep")
    data object AddRecipe: NavScreen(R.drawable.add, "AddRecipe")
    data object BulkPrepEdit: NavScreen(R.drawable.draw, "Edit Bulk Prep")
    data object VeganEdit: NavScreen(R.drawable.draw, "Edit Vegan")
    data object WeightGainEdit: NavScreen(R.drawable.draw, "Edit Weight Gain")
    data object WeightLossEdit: NavScreen(R.drawable.draw, "Edit Weight Loss")
    data object BulkPrepView: NavScreen(R.drawable.draw, "View Bulk Prep")
    data object VeganView: NavScreen(R.drawable.draw, "View Vegan")
    data object WeightLossView: NavScreen(R.drawable.draw, "View weight loss")
    data object WeightGainView: NavScreen(R.drawable.draw, "View weight gain")
    data object WeightLoss: NavScreen(R.drawable.home, "WeightLoss")
    data object WeightGain: NavScreen(R.drawable.home, "WeightGain")
    data object Vegan: NavScreen(R.drawable.home, "Vegan")
    data object Settings: NavScreen(R.drawable.settings, "Settings")
    data object Track: NavScreen(R.drawable.home, "Track")


}

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    var selectedRecipe: Recipe? = null
    var selectedContact: Contact? = null
    val database = FirebaseDatabase.getInstance().reference
    val contactDAO = ContactDAO(database)
    val contactRepo = ContactRepository(contactDAO)
    val authRepo = AuthRepository(auth = FirebaseAuth.getInstance(), contactRepo = contactRepo)


    NavHost(
        navController,
        startDestination = NavScreen.Start.route
    ) {

        composable(NavScreen.Start.route) {
            StartScreen(navController = navController) {
                navController.navigate(NavScreen.Login.route)
            }
        }

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
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(NavScreen.Home.route) {
            HomeScreen(
                navController = navController,
                onClickToBulkPrep = {
                    navController.navigate(NavScreen.BulkPrep.route)
                },
                onClickToWeightLoss = {
                    navController.navigate(NavScreen.WeightLoss.route)
                },
                onClickToWeightGain = {
                    navController.navigate(NavScreen.WeightGain.route)
                },
                onClickToVegan = {
                    navController.navigate(NavScreen.Vegan.route)
                },
                onClickToTrack = {
                    navController.navigate(NavScreen.Track.route)
                },
            )
        }
        composable(NavScreen.BulkPrep.route) {
            BulkPrepScreen(
                navController = navController,
                onClickToRecipeDetailScreen = { recipeId ->
                    navController.navigate("${NavScreen.BulkPrepView.route}/${recipeId.trim()}")
                }
            ) {
                navController.navigate(NavScreen.AddRecipe.route)
            }
        }
        composable("${NavScreen.BulkPrepView.route}/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            recipeId?.let {
                val vm: RecipeDetailViewModel =
                    viewModel(factory = RecipeDetailViewModel.Factory(recipeId))
                RecipeDetailsScreen(
                    recipeName = it,
                    navController = navController,
                    recipe = Recipe(recipeId),
                    onClickToEditRecipe = { recipeId ->
                        navController.navigate("${NavScreen.Edit.route}/${recipeId.trim()}")
                    },
                    recipeId = it,
                    vm = vm
                )
            }
        }

        composable(NavScreen.Vegan.route) {
            VeganScreen(
                navController = navController,
                onClickToRecipeDetailScreen = { recipeId ->
                    navController.navigate("${NavScreen.VeganView.route}/${recipeId.trim()}")
                }
            ) {
                navController.navigate(NavScreen.AddRecipe.route)
            }
        }
        composable("${NavScreen.VeganView.route}/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            recipeId?.let {
                val vm: RecipeDetailViewModel =
                    viewModel(factory = RecipeDetailViewModel.Factory(recipeId))
                RecipeDetailsScreen(
                    recipeName = it,
                    navController = navController,
                    recipe = Recipe(recipeId),
                    onClickToEditRecipe = { recipeId ->
                        navController.navigate("${NavScreen.Edit.route}/${recipeId.trim()}")
                    },
                    recipeId = it,
                    vm = vm
                )
            }
        }


        composable(NavScreen.WeightGain.route) {
            WeightGainScreen(
                navController = navController,
                onClickToAddRecipe = {
                    navController.navigate(NavScreen.AddRecipe.route)
                },
                onClickToRecipeDetailScreen = { recipeId ->
                    navController.navigate("${NavScreen.WeightGainView.route}/$recipeId")
                }
            )
        }
        composable("${NavScreen.WeightGainView.route}/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            recipeId?.let {
                val vm: RecipeDetailViewModel = viewModel(factory = RecipeDetailViewModel.Factory(recipeId))
                vm.name?.let {
                    RecipeDetailsScreen(
                        recipeName = it,
                        navController = navController,
                        recipe = Recipe(),
                        onClickToEditRecipe = { recipeId ->
                            navController.navigate("${NavScreen.WeightGainEdit.route}/$recipeId")
                        },
                        recipeId = it,
                        vm = vm
                    )
                }
            }
        }

        composable(NavScreen.WeightLoss.route) {
            WeightLossScreen(
                navController = navController,
                onClickToAddRecipe = {
                    navController.navigate(NavScreen.AddRecipe.route)
                },
                onClickToRecipeDetailScreen = { recipeId ->
                    navController.navigate("${NavScreen.WeightLossView.route}/$recipeId")
                }
            )
        }
        composable("${NavScreen.WeightLossView.route}/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            recipeId?.let {
                val vm: RecipeDetailViewModel = viewModel(factory = RecipeDetailViewModel.Factory(recipeId))
                vm.name?.let {
                    RecipeDetailsScreen(
                        recipeName = it,
                        navController = navController,
                        recipe = Recipe(),
                        onClickToEditRecipe = { recipeId ->
                            navController.navigate("${NavScreen.WeightLossEdit.route}/$recipeId")
                        },
                        recipeId = it,
                        vm = vm
                    )
                }
            }
        }

                composable(NavScreen.AddRecipe.route) {
                    AddScreen(
                        navController = navController
                    ) {
                        navController.popBackStack()
                    }
                }

                composable(NavScreen.Track.route) {
                    TrackScreen(
                        navController = navController
                    )
                }

                composable(NavScreen.Add.route) {
                    AddScreen(navController = navController,
                        onClickToHome = { navController.popBackStack() })
                }

                composable("${NavScreen.Edit.route}/{recipeId}") { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getString("recipeId")
                    recipeId?.let {
                        val vm: EditViewModel = viewModel(factory = EditViewModel.Factory(recipeId))
                            EditScreen(
                                recipeId = it,
                                vm = vm,
                                navController = navController,
                                onClickToHome = {
                                    navController.popBackStack(NavScreen.Home.route, false)
                               })
                         }
                }



                composable(NavScreen.Settings.route) {
                    SettingsScreen(navController = navController, authRepo = authRepo)

                }

                composable(NavScreen.Exit.route) {
                    ContactApplication.container.authRepository.signOut()
                    exitProcess(0)
                }
            }
        }