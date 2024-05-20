package tkuik.alexkarav.veterinary.ui.components.main_page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tkuik.alexkarav.veterinary.R
import tkuik.alexkarav.veterinary.ui.components.navigation.BottomNavigationElement
import tkuik.alexkarav.veterinary.util.DateHumanize

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun MainPage(username: String = "Alexander") {
    val navController = rememberNavController()
    val bottomNavigationItem = listOf(
        BottomNavigationElement(label = "Главная", name = "home", iconDefault = Icons.Default.Home, iconChosen = Icons.Filled.Home),
        BottomNavigationElement(label = "Мои питомцы", name = "pets", iconDefault = ImageVector.vectorResource(
            R.drawable.paw_icon), iconChosen = ImageVector.vectorResource(R.drawable.paw_icon)),
        BottomNavigationElement(label = "Услуги", name = "shop", iconDefault = Icons.Default.ShoppingCart, iconChosen = Icons.Filled.ShoppingCart)
    )
    var currentScreen by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavigationItem.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == currentScreen,
                        onClick = { currentScreen = index; navController.navigate(item.name) },
                        icon = { Icon(imageVector = if(currentScreen == index) item.iconChosen else item.iconDefault, contentDescription = null) },
                        alwaysShowLabel = true,
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        )
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 20.dp)) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.primary)
                        Text("${DateHumanize().getCurrentTimeOfDay()} $username", fontSize = 16.sp, modifier = Modifier.padding(top = 5.dp, start = 5.dp))
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(5) {
                            DealsCards()
                        }
                    }
                }
            }
            composable("pets") {

            }

            composable("shop") {

            }
        }
    }
}

@Preview
@Composable
fun DealsCards(
    title: String = ""
) {
    ElevatedCard(modifier = Modifier.size(width = 300.dp, height = 100.dp).padding(end = 20.dp)) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title)
        }
    }
}
