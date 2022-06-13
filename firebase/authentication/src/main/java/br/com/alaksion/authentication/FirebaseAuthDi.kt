package br.com.alaksion.authentication

import com.google.firebase.auth.FirebaseAuth
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val FirebaseAuthDi = DI.Module("firebase-auth") {

    bind<FirebaseAuth>() with singleton {
        FirebaseAuth.getInstance()
    }
}