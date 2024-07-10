package  com.example.starbuy.di

import android.content.Context
import com.example.starbuy.model.net.createApiService
import com.example.starbuy.model.repository.user.UserRepository
import com.example.starbuy.model.repository.user.UserRepositoryImpl
import com.example.starbuy.ui.features.signIn.SignInViewModel
import com.example.starbuy.ui.features.signUp.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    single { androidContext().getSharedPreferences("data", Context.MODE_PRIVATE) }
    single { createApiService() }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }

}