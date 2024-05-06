package tkuik.alexkarav.veterinary.ui.components.auth_screen

sealed class AuthScreenUIEvents {
    data class OnAuthButtonPressed(val login: String, val password: String): AuthScreenUIEvents()
    data object OnRegisterButtonPressed : AuthScreenUIEvents()
}