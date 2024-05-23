
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.components.CustomButton


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    onClickToBulkPrep: () -> Unit,
    onClickToWeightLoss: () -> Unit,
    onClickToWeightGain: () -> Unit,
    onClickToVegan: () -> Unit,
    onClickToTrack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    modifier = Modifier.padding(top = 10.dp, start = 16.dp, bottom = 8.dp)
                )

                CustomButton(
                    text = "Bulk Prep",
                    clickButton = onClickToBulkPrep
                )

                CustomButton(
                    text = "Weight Loss",
                    clickButton = onClickToWeightLoss
                )

                CustomButton(
                    text = "Weight Gain",
                    clickButton = onClickToWeightGain
                )

                CustomButton(
                    text = "Vegan",
                    clickButton = onClickToVegan
                )

                Spacer(modifier = Modifier.height(80.dp))


                CustomButton(
                    text = "Track",
                    clickButton = onClickToTrack
                )
            }
        }
    }
}
