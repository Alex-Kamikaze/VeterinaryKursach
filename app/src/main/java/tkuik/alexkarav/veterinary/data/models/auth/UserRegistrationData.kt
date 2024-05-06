package tkuik.alexkarav.veterinary.data.models.auth

data class UserRegistrationData(
    val userName: String,
    val userSurname: String,
    val userEmail: String,
    val userPhone: String,
    val userPassword: String
)
