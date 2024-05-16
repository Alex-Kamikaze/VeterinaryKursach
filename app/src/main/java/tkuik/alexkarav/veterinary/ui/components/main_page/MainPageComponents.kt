package tkuik.alexkarav.veterinary.ui.components.main_page

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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tkuik.alexkarav.veterinary.util.DateHumanize

@Preview(showBackground = true)
@Composable
fun MainPage(username: String = "Alexander") {
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

@Preview
@Composable
fun DealsCards() {
    ElevatedCard(modifier = Modifier.size(width = 300.dp, height = 100.dp)) {
        Text("Здесь будут предложения о скидках")
    }
}
