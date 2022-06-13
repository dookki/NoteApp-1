package br.com.alaksion.noteapp

import br.com.alaksion.authentication.FirebaseAuthDi
import br.com.alaksion.feature_login.presentation.AuthDi
import org.kodein.di.DI

val AppDi = DI {
    import(FirebaseAuthDi)
    import(AuthDi)
}