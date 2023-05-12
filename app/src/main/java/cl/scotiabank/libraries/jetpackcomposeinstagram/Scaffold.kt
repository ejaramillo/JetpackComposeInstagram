package cl.scotiabank.libraries.jetpackcomposeinstagram

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ScaffoldExample() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Color.Red)
        ) {
            MyTopAppBar {
                coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar("Has pulsado $it") }
            }
        }
    }
}

@Composable
fun MyBottomNavigation() {
    BottomNavigation {
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "home") },
            label = { Text(text = "Home") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "fav") },
            label = { Text(text = "Home") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "fav") },
            label = { Text(text = "Home") }
        )
    }
}

// también se le llama toolbar
@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit) {
    TopAppBar(
        title = { Text(text = "Mi primera toolbar") },
        backgroundColor = Color.Red,
        contentColor = Color.White,
        elevation = 4.dp,// valor por defecto
        navigationIcon = {
            IconButton(onClick = { onClickIcon.invoke("back") }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back"
                )
            }
        },
        // íconod que van al final de la toolbar
        actions = {
            IconButton(onClick = { onClickIcon.invoke("search") }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search"
                )
            }

            IconButton(onClick = { onClickIcon.invoke("back") }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "back"
                )
            }
        }
    )
}