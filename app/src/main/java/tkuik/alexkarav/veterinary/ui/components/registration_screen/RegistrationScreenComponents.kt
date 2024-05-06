package tkuik.alexkarav.veterinary.ui.components.registration_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tkuik.alexkarav.veterinary.ui.components.auth_screen.LoginInfoTextField
import tkuik.alexkarav.veterinary.ui.components.auth_screen.PasswordTextField

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier, onUiEvents: (RegistrationScreenUIEvents) -> Unit) {
    val nameInputState = remember { mutableStateOf("") }
    val surnameInputState = remember { mutableStateOf("") }
    val emailInputState = remember { mutableStateOf("") }
    val phoneInputState = remember { mutableStateOf("") }
    val passwordInputState = remember { mutableStateOf("") }
    val repeatPasswordInputState = remember { mutableStateOf("") }
    val passwordVisibleState = remember { mutableStateOf(false) }
    val repeatPasswordVisibleState = remember { mutableStateOf(false) }

    val registerButtonEnabled by remember {
        derivedStateOf {
            nameInputState.value.isNotEmpty() &&
            surnameInputState.value.isNotEmpty() &&
            emailInputState.value.isNotEmpty() &&
            phoneInputState.value.isNotEmpty() &&
            passwordInputState.value.isNotEmpty() &&
            repeatPasswordInputState.value.isNotEmpty() &&
            passwordInputState.value == repeatPasswordInputState.value
        }
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(start = 40.dp, end = 40.dp, bottom = 80.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
        Text("Регистрация", fontWeight = FontWeight.Black, fontSize = 24.sp)
        Text("Зарегистрируйтесь, чтобы получить доступ к нашему сервису", modifier = Modifier.padding(top = 10.dp), fontSize = 15.sp)
        LoginInfoTextField(state = nameInputState, hint = "Введите свое имя")
        LoginInfoTextField(state = surnameInputState, hint = "Введите свою фамилию")
        LoginInfoTextField(state = emailInputState, hint = "Введите свою электронную почту")
        LoginInfoTextField(state = phoneInputState, hint = "Введите свой номер телефона")
        PasswordTextField(textInputState = passwordInputState, passwordVisibleState = passwordVisibleState, hint = "Введите пароль")
        PasswordTextField(textInputState = repeatPasswordInputState, passwordVisibleState = repeatPasswordVisibleState, hint = "Повторите пароль")
        Button(onClick = { onUiEvents(RegistrationScreenUIEvents.OnRegisterButtonPressed(nameInputState.value, surnameInputState.value, emailInputState.value, phoneInputState.value, passwordInputState.value)) }, colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer, contentColor = MaterialTheme.colorScheme.primaryContainer), modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), shape = RoundedCornerShape(8.dp), enabled = registerButtonEnabled) {
            Text("Авторизоваться")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen(Modifier) {}
}