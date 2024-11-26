package blackorbs.dev.travelapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import blackorbs.dev.travelapp.ui.theme.TravelAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelAppTheme {
                MainScreen()
            }
        }
    }
}

@Serializable
sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data class Detail(val id: Int) : Screen
}