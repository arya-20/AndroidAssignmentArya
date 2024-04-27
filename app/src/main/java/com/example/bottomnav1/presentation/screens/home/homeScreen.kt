import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bottomnav1.data.contact1.Contact
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.components.CustomButton
import com.example.bottomnav1.presentation.navigation.NavScreen
import com.example.bottomnav1.presentation.screens.bulk.BulkViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController,
               onIndexChange: (Contact) -> Unit,
               onClickToBulkPrep: () -> Unit) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Column {
            CustomButton("Bulk Prep") {
                onClickToBulkPrep()  }
        }
    }
}

