package  com.example.starbuy.di

import com.example.starbuy.ui.features.signIn.SignInViewModel
import com.example.starbuy.ui.features.signUp.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    viewModel {SignUpViewModel()}
    viewModel { SignInViewModel() }

}