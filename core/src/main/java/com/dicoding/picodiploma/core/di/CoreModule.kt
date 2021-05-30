package com.dicoding.picodiploma.core.di


import androidx.room.Room
import com.dicoding.picodiploma.core.data.Repository
import com.dicoding.picodiploma.core.data.room.FilmDatabase
import com.dicoding.picodiploma.core.data.source.local.LocalDataSource
import com.dicoding.picodiploma.core.data.source.remote.ApiService
import com.dicoding.picodiploma.core.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.core.domain.repository.IRepository
import com.dicoding.picodiploma.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CoreModule {
    val databaseModule = module {
        factory { get<FilmDatabase>().dao() }
        single {
            val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
            val factory = SupportFactory(passphrase)
            Room.databaseBuilder(
                androidContext(),
                FilmDatabase::class.java, "Film.db"
            ).fallbackToDestructiveMigration()
                    .openHelperFactory(factory)
                    .build()
        }
    }

    val networkModule = module {
        single {
            val hostname = "themoviedb.org"
            val certificatePinner = CertificatePinner.Builder()
                    .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0==")
                    .build()
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        factory { AppExecutors() }
        single<IRepository> { Repository(get(), get(), get()) }
    }
}