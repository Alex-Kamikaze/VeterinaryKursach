package tkuik.alexkarav.veterinary.ui.components.registration_screen

sealed class RegistrationScreenUIEvents {
    data class OnRegisterButtonPressed(
        val userName: String,
        val userSurname: String,
        val userEmail: String,
        val userPhone: String,
        val userPassword: String
    ): RegistrationScreenUIEvents()

}