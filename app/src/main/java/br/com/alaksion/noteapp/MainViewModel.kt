package br.com.alaksion.noteapp

import androidx.lifecycle.ViewModel
import com.example.navigation.AuthRouter
import com.example.navigation.HomeRouter
import com.example.navigation.NavRouter
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth

internal class MainViewModel(
    private val auth: FirebaseAuth
) : ViewModel() {
    fun getStartDestination(): NavRouter {
        val isUserLoggedIn = auth.currentUser != null
        return if (isUserLoggedIn) HomeRouter else AuthRouter
    }
}