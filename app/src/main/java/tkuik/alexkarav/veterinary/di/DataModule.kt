package tkuik.alexkarav.veterinary.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.serializer.JacksonSerializer
import tkuik.alexkarav.veterinary.data.DataStoreManager
import tkuik.alexkarav.veterinary.data.repository.VeterinaryAppRepository
import tkuik.alexkarav.veterinary.domain.repository.VeterinaryAppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideRepository(dataStoreManager: DataStoreManager, supabaseClient: SupabaseClient): VeterinaryAppRepository {
        return VeterinaryAppRepositoryImpl(dataStoreManager, supabaseClient)
    }

    @Provides
    @Singleton
    fun provideSupabase(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://wcucvvcjydluqiwdzmqf.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndjdWN2dmNqeWRsdXFpd2R6bXFmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ5ODUyNTgsImV4cCI6MjAzMDU2MTI1OH0.tz8Lq6TwFi-dH4TfDmd_0W70L2JLnsYpqXntYeNtvh4"
        ) {
            defaultSerializer = JacksonSerializer()
            install(Auth)

        }
    }
}