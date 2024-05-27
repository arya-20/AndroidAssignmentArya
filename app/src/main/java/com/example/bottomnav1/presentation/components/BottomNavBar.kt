package com.example.bottomnav1.presentation.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottomnav1.presentation.navigation.NavScreen

@Composable
fun BottomNavBar(navController: NavController) {
    BottomNavigation(
        modifier = Modifier
            .background(color = Color.Gray, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .semantics { contentDescription = "bottom_nav" },
        contentColor = Color.White,
        elevation = 8.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        createListOfItems().forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.route) },
                label = { Text(text = item.route, fontSize = 9.sp) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.LightGray.copy(alpha = 0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun createListOfItems(): List<NavScreen> {
    return listOf(
        NavScreen.Home,
        NavScreen.Settings,
        NavScreen.Exit
    )
}
