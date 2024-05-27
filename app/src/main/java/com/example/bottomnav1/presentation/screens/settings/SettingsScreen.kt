
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.screens.settings.SettingsViewModel



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavHostController,
    authRepo: AuthRepo,
    viewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory(authRepo))
) {

    val isLightModeEnabled = viewModel.isLightModeEnabled(lightTheme = true)
    val isNotificationsEnabled = viewModel.isNotificationsEnabled()
    var showDeleteDialog by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = ("Settings "),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Dark Mode",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(end = 40.dp)
                )
                Switch(
                    checked = isLightModeEnabled,
                    onCheckedChange = { isChecked ->

                    }
                )
            }
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Enable Notifications",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(end = 40.dp)
                )
                Switch(
                    checked = isNotificationsEnabled,
                    onCheckedChange = { isChecked ->
                    }
                )
            }
            Button(
                onClick = { showDeleteDialog = true },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Delete Account")
            }
            Button(
                onClick = { },
                modifier = Modifier.padding(bottom = 8.dp)

            ) {
                Text(text = "Change Password")
            }
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDeleteDialog = false
                        viewModel.deleteAccount(navController)
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("No")
                    }
                },
                title = { Text(text = "Confirm Delete") },
                text = { Text("Are you sure you want to delete your account?") }
            )
        }
    }
}
