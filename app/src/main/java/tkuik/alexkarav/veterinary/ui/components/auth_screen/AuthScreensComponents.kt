package tkuik.alexkarav.veterinary.ui.components.auth_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tkuik.alexkarav.veterinary.R

@Composable
fun AuthScreen() {
    val loginInputState = remember { mutableStateOf("")}
    val passwordInputState = remember { mutableStateOf("") }
    val passwordVisibleState = remember { mutableStateOf(false) }
    val authButtonEnabled by remember {
        derivedStateOf {
            loginInputState.value.isNotEmpty() && passwordInputState.value.isNotEmpty()
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 40.dp, end = 40.dp, bottom = 80.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
        Text("Авторизация", fontWeight = FontWeight.Black, fontSize = 24.sp)
        Text("Авторизуйтесь, чтобы получить доступ к своему аккаунту", modifier = Modifier.padding(top = 10.dp), fontSize = 15.sp)
        LoginInfoTextField(state = loginInputState, hint = "Ваш логин")
        PasswordTextField(textInputState = passwordInputState, passwordVisibleState = passwordVisibleState, hint = "Ваш пароль")
        Button(onClick = {}, colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer, contentColor = MaterialTheme.colorScheme.primaryContainer), modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), shape = RoundedCornerShape(8.dp), enabled = authButtonEnabled) {
            Text("Авторизоваться")
        }
        Text("У вас еще нет аккаунта?", modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), textAlign = TextAlign.Center)
        OutlinedButton(onClick = { /*TODO*/ }, colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSecondaryContainer), shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
            Text("Зарегистрироваться")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthScreenPreview() {
    AuthScreen()
}

@Composable
private fun LoginInfoTextField(state: MutableState<String>, hint: String) {
    OutlinedTextField(value = state.value, onValueChange = {state.value = it}, modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp), placeholder = { Text(hint, fontWeight = FontWeight.Light)}, shape = RoundedCornerShape(8.dp) )
}

@Composable
private fun PasswordTextField(textInputState: MutableState<String>, passwordVisibleState: MutableState<Boolean>, hint: String) {
    OutlinedTextField(
        value = textInputState.value,
        onValueChange = { textInputState.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        placeholder = { Text(hint, fontWeight = FontWeight.Light) },
        shape = RoundedCornerShape(8.dp),
        trailingIcon = { Icon(painter = painterResource(id = if (passwordVisibleState.value) R.drawable.password_visible_icon else R.drawable.password_invisible_icon), contentDescription = null, modifier = Modifier.clickable {
            passwordVisibleState.value = !passwordVisibleState.value
        }) },
        visualTransformation = if(passwordVisibleState.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}