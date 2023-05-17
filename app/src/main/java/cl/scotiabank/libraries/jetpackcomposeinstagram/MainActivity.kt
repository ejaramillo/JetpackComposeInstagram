package cl.scotiabank.libraries.jetpackcomposeinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.scotiabank.libraries.jetpackcomposeinstagram.model.Routes
import cl.scotiabank.libraries.jetpackcomposeinstagram.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Pantalla1.route
                    ) {
                        composable(Routes.Pantalla1.route) { Screen1(navController = navigationController) }
                        composable(Routes.Pantalla2.route) { Screen2(navController = navigationController) }
                        composable(Routes.Pantalla3.route) { Screen3(navController = navigationController) }
                        composable(
                            route = Routes.Pantalla4.route,
                            arguments = listOf(navArgument("age") { type = NavType.IntType })
                        ) { backStackEntry ->
                            Screen4(
                                navController = navigationController,
                                age = backStackEntry.arguments?.getInt("age") ?: 0
                            )
                        }
                        composable(
                            route = Routes.Pantalla5.route,
                            arguments = listOf(navArgument("name") { defaultValue = "Pepe" })
                        ) { backStackEntry ->
                            Screen5(
                                navController = navigationController,
                                name = backStackEntry.arguments?.getString("name")
                            )
                        }
                    }
                }
            }
        }
    }
}
