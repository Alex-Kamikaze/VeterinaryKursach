package tkuik.alexkarav.veterinary.data.repository

import kotlinx.coroutines.flow.Flow
import tkuik.alexkarav.veterinary.data.models.auth.UserRegistrationData

interface VeterinaryAppRepository {
    suspend fun loginUser(userLogin: String, userPassword: String): Result<Boolean>

    fun getIntroScreenCompletion(): Flow<Boolean>

    suspend fun setIntroScreenCompletion(completed: Boolean)

    suspend fun registerUser(userData: UserRegistrationData): Result<Boolean>

    fun getUsername(): Flow<String>
}