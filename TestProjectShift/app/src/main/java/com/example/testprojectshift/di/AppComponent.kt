package com.example.testprojectshift.di

import android.content.Context
import androidx.room.Room
import com.example.testprojectshift.UnsafeOkHttpClient
import com.example.testprojectshift.data.db.BankCardHistoryDataBase
import com.example.testprojectshift.data.db.BankCardInfoDao
import com.example.testprojectshift.data.db.BankCardRepository
import com.example.testprojectshift.data.db.BankCardRepositoryImpl
import com.example.testprojectshift.data.server.BankInfoService
import com.example.testprojectshift.presintaion.MainActivity
import com.example.testprojectshift.presintaion.viewHistory
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Component(modules = [RetrClientModule::class, DataBaseModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun plus(activity: viewHistory)
}
@Module
class RetrClientModule{

    var okHttpClient: OkHttpClient? = UnsafeOkHttpClient.getUnsafeOkHttpClient()

    @Provides
    fun provideRetrofit():Retrofit = Retrofit.Builder()
        .baseUrl("https://lookup.binlist.net/")
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())//TODO:
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideBankInfoService(
        retrofit: Retrofit
    ) : BankInfoService = retrofit.create()
}

@Module(
    includes = [
        RepositoriesModule::class
    ]
)
class DataBaseModule(private val context: Context){
    @Provides
    fun provideBankCardHistoryDataBase(): BankCardHistoryDataBase = Room
        .databaseBuilder(
            context,
            BankCardHistoryDataBase::class.java,
            "database_c"
        )
        .allowMainThreadQueries()
        .build()

    @Provides
    fun provideContactDao(
        db: BankCardHistoryDataBase
    ): BankCardInfoDao = db.getBankCardInfoDao()
}

@Module
abstract class RepositoriesModule {
    @Binds
    abstract fun bindsBankRepos(impl: BankCardRepositoryImpl): BankCardRepository
}
