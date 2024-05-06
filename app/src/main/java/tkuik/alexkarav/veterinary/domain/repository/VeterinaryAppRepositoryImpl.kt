package tkuik.alexkarav.veterinary.domain.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import tkuik.alexkarav.veterinary.data.DataStoreManager
import tkuik.alexkarav.veterinary.data.models.auth.AuthorizationResults
import tkuik.alexkarav.veterinary.data.repository.VeterinaryAppRepository
import javax.inject.Inject

class VeterinaryAppRepositoryImpl @Inject constructor(private val dataStoreManager: DataStoreManager, private val supabaseClient: SupabaseClient): VeterinaryAppRepository {
    override suspend fun loginUser(userLogin: String, userPassword: String): Result<Any> {
        return try {
            supabaseClient.auth.signInWith(Email) {
                email = userLogin
                password = userPassword
            }
            Result.success(AuthorizationResults.Success)
        }
        catch(e: Exception) {
            Result.failure(e)
        }

    }
    override fun getIntroScreenCompletion(): Flow<Boolean> {
        return dataStoreManager.getIntroScreenCompletion()
    }

    override suspend fun setIntroScreenCompletion(completed: Boolean) {
        dataStoreManager.insertIntroScreenCompletion(completed)
    }
}