package tkuik.alexkarav.veterinary.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tkuik.alexkarav.veterinary.R
import tkuik.alexkarav.veterinary.domain.viewmodels.MainViewModel
import tkuik.alexkarav.veterinary.ui.components.IntroScreenPath
import tkuik.alexkarav.veterinary.ui.components.auth_screen.AuthScreen
import tkuik.alexkarav.veterinary.ui.components.intro_screen_components.IntroScreen
import tkuik.alexkarav.veterinary.ui.components.intro_screen_components.IntroScreenModel
import tkuik.alexkarav.veterinary.ui.theme.VeterinaryTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.showSplash.value
        }
        setContent {
            VeterinaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainViewModel = hiltViewModel()
                    val navController = rememberNavController()
                    val introScreenCompleted by mainViewModel.introScreenCompleted.collectAsState()
                    //TODO: заменить названия путей на нормальные классы с прописанными путями
                    NavHost(navController = navController, startDestination = if(introScreenCompleted == true) "auth" else "intro_screen") {
                        composable(IntroScreenPath.path) {
                            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                val screens = listOf(IntroScreenModel("Команда специалистов", "Профессиональные ветеринары с огромным опытом работы с животными", R.drawable.intro_screen_image_1), IntroScreenModel("Удобство и доступность", "Быстрые анализы и доступ к результатам через мобильное приложение", R.drawable.intro_screen_image_2), IntroScreenModel("Высокие технологии", "Современные технологии в медицине для помощи вашему питомцу", R.drawable.intro_screen_image_3))
                                IntroScreen(screens = screens, modifier = Modifier
                                    .fillMaxSize()
                                    .padding(40.dp)) {
                                    mainViewModel.setIntroScreenCompletion()
                                    navController.navigate("auth") {
                                        popUpTo("intro_screen") {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        }
                        composable("auth") {
                            AuthScreen {}
                        }
                    }
                }
            }
        }
    }
}