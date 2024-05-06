package tkuik.alexkarav.veterinary.ui.components.intro_screen_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tkuik.alexkarav.veterinary.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(modifier: Modifier = Modifier, screens: List<IntroScreenModel>, onButtonPressed: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = {
        screens.size
    })

    Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(state = pagerState) { page ->
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = ImageVector.vectorResource(screens[page].image), contentDescription = null, modifier = Modifier.size(200.dp), tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(50.dp))
                Text(screens[page].title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text(screens[page].subTitle, fontWeight = FontWeight.Medium, fontSize = 16.sp, textAlign = TextAlign.Center, modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp))

            }
        }
        Button(onClick = onButtonPressed, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer), shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth().padding(start = 40.dp, end = 40.dp)) {
            Text(if (pagerState.currentPage == 2) "Далее" else "Пропустить")
        }
    }
}