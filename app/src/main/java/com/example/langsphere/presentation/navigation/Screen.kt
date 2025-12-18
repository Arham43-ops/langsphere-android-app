package com.example.langsphere.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Lesson : Screen("lesson/{languageId}") {
        fun createRoute(languageId: String) = "lesson/$languageId"
    }
}
