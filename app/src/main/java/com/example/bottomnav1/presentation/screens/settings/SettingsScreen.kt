
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottomnav1.R
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.screens.settings.SettingsViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: SettingsViewModel = viewModel(),
) {
    //val keyboardController = LocalSoftwareKeyboardController.current

    val isLightModeEnabled = viewModel.isLightModeEnabled(lightTheme = true)
    val isNotificationsEnabled = viewModel.isNotificationsEnabled()

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
                text = stringResource(R.string.settings),
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
                onClick = { /* TODO: Implement delete account functionality */ },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Delete Account")
            }
            Button(
                onClick = { /* TODO: Implement change password functionality */ },
                modifier = Modifier.padding(bottom = 8.dp)

            ) {
                Text(text = "Change Password")
            }
        }
    }
}
