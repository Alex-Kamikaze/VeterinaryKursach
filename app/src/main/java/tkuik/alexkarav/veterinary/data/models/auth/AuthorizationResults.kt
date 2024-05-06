package tkuik.alexkarav.veterinary.data.models.auth

sealed class AuthorizationResults {
    data object Success: AuthorizationResults()
    data class Error(val error: String, val exception: Exception): AuthorizationResults()
}