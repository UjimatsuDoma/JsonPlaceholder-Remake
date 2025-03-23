package prac.tanken.jsonplaceholder.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import prac.tanken.jsonplaceholder.core.NetworkConfig
import prac.tanken.jsonplaceholder.core.HeadersInterceptor
import prac.tanken.jsonplaceholder.datasource.NetworkDataSource
import prac.tanken.jsonplaceholder.detail.ItemDetailContract
import prac.tanken.jsonplaceholder.detail.ItemDetailPresenter
import prac.tanken.jsonplaceholder.detail.usecase.GetItemCommentsUseCase
import prac.tanken.jsonplaceholder.list.ItemsListContract
import prac.tanken.jsonplaceholder.list.ItemsListPresenter
import prac.tanken.jsonplaceholder.list.usecase.GetItemsListUseCase
import prac.tanken.jsonplaceholder.repository.*
import prac.tanken.jsonplaceholder.repository.mapper.ItemsMapper
import com.readystatesoftware.chuck.ChuckInterceptor
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    factory { GetItemsListUseCase(get()) }
    factory { GetItemCommentsUseCase(get()) }
    factory<ItemsListContract.Presenter> { ItemsListPresenter(get(), Dispatchers.Main) }
    factory<ItemDetailContract.Presenter> { ItemDetailPresenter(get(), Dispatchers.Main) }
    single<ItemsRepository> { ItemsRepository.NetworkRepository(get()) }
    single { NetworkDataSource(get(), get()) }
    single { ItemsMapper() }
    single<ItemsApi> { get<Retrofit>().create(ItemsApi::class.java) }

    single {
        Retrofit.Builder()
            .apply {
                addCallAdapterFactory(CoroutineCallAdapterFactory())
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(NetworkConfig.API_URL)
                client(get())
            }
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ChuckInterceptor>())
            .addInterceptor(get<HeadersInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single { ChuckInterceptor(androidContext()) }
    single { HeadersInterceptor() }
    factory {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return@factory interceptor
    }
}