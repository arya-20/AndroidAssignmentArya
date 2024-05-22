
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bottomnav1.R
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
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                androidx.compose.material.Text(
                    text = stringResource(R.string.home),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
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

                Spacer(modifier = Modifier.height(40.dp))


                CustomButton(
                    text = "Track",
                    clickButton = onClickToTrack
                )
            }
        }
    }
}
