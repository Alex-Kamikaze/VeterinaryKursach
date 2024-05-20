package tkuik.alexkarav.veterinary.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

    suspend fun insertIntroScreenCompletion(completed: Boolean) {
        context.dataStore.edit { settings ->
            settings[introCompletedKey] = completed
        }
    }

    fun getIntroScreenCompletion(): Flow<Boolean> {
        return context.dataStore.data.map { settings ->
            settings[introCompletedKey] ?: false
        }
    }

    fun getUserName(): Flow<String> {
        return context.dataStore.data.map { settings ->
            settings[usernameKey] ?: ""
        }
    }

    suspend fun saveUserInformationForAuthorization(userEmail: String, userPassword: String) {
        context.dataStore.edit { settings ->
            settings[userEmailKey] = userEmail
            settings[userPasswordKey] = userPassword
        }
    }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit {
            it[usernameKey] = username
        }
    }


    companion object {
        val introCompletedKey = booleanPreferencesKey("INTRO_COMPLETED")
        val usernameKey = stringPreferencesKey("USERNAME")
        val userEmailKey = stringPreferencesKey("EMAIL")
        val userPasswordKey = stringPreferencesKey("PASSWORD")
    }
}