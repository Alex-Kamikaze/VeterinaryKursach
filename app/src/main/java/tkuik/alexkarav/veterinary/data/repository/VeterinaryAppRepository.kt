package tkuik.alexkarav.veterinary.data.repository

import kotlinx.coroutines.flow.Flow

interface VeterinaryAppRepository {
    suspend fun loginUser(userLogin: String, userPassword: String): Result<Any>

    fun getIntroScreenCompletion(): Flow<Boolean>

    suspend fun setIntroScreenCompletion(completed: Boolean)
}