package  com.example.starbuy.di

import android.content.Context
import androidx.room.Room
import com.example.starbuy.model.db.AppDatabase
import com.example.starbuy.model.net.createApiService
import com.example.starbuy.model.repository.product.ProductRepository
import com.example.starbuy.model.repository.product.ProductRepositoryImpl
import com.example.starbuy.model.repository.user.UserRepository
import com.example.starbuy.model.repository.user.UserRepositoryImpl
import com.example.starbuy.ui.features.main.MainViewModel
import com.example.starbuy.ui.features.signIn.SignInViewModel
import com.example.starbuy.ui.features.signUp.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    single { androidContext().getSharedPreferences("data", Context.MODE_PRIVATE) }
    single { createApiService() }

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_dataBase.db").build() }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<ProductRepository> { ProductRepositoryImpl(get(), get<AppDatabase>().productDao()) }

    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { (isNetConnected: Boolean) -> MainViewModel(get(), isNetConnected)  }

}