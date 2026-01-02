package com.example.langsphere.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.langsphere.presentation.achievements.AchievementsScreen

import com.example.langsphere.presentation.home.HomeScreen
import com.example.langsphere.presentation.leaderboard.LeaderboardScreen
import com.example.langsphere.presentation.lesson.LessonScreen
import com.example.langsphere.presentation.profile.ProfileScreen
import com.example.langsphere.presentation.vocabulary.VocabularyScreen

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("home_tab", "Home", Icons.Default.Home)
    object Leaderboard : BottomNavItem("leaderboard_tab", "Rank", Icons.Default.Star)
    object Profile : BottomNavItem("profile_tab", "Profile", Icons.Default.Person)
}

@Composable
fun MainScreen(
    onLogout: () -> Unit
) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(

        bottomBar = {
            if (currentRoute != "lesson/{lessonId}" && currentRoute != "lessonList/{languageId}" && currentRoute != "achievements" && currentRoute != "vocabulary" && currentRoute != "editProfile") { 
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    val items = listOf(BottomNavItem.Home, BottomNavItem.Leaderboard, BottomNavItem.Profile)
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = currentRoute == item.route,
                            onClick = {
                                bottomNavController.navigate(item.route) {
                                    popUpTo(bottomNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    onLanguageSelected = { languageId ->
                        bottomNavController.navigate("lessonList/$languageId")
                    },
                    onNavigateToVocab = { bottomNavController.navigate("vocabulary") }
                )
            }
            composable(BottomNavItem.Leaderboard.route) {
                LeaderboardScreen()
            }
            composable(BottomNavItem.Profile.route) {
                ProfileScreen(
                    onLogout = onLogout,
                    onNavigateToAchievements = { bottomNavController.navigate("achievements") },
                    onNavigateToEditProfile = { bottomNavController.navigate("editProfile") }
                )
            }
            composable("achievements") {
                AchievementsScreen(onNavigateBack = { bottomNavController.popBackStack() })
            }
            composable("editProfile") {
                com.example.langsphere.presentation.profile.EditProfileScreen(
                    onNavigateBack = { bottomNavController.popBackStack() }
                )
            }

            composable("vocabulary") {
                VocabularyScreen(onNavigateBack = { bottomNavController.popBackStack() })
            }
            composable("lessonList/{languageId}") { backStackEntry ->
                val languageId = backStackEntry.arguments?.getString("languageId") ?: ""
                com.example.langsphere.presentation.lesson.LessonListScreen(
                    languageId = languageId,
                    onNavigateBack = { bottomNavController.popBackStack() },
                    onLessonSelected = { lessonId ->
                        bottomNavController.navigate("lesson/$lessonId")
                    }
                )
            }
            composable("lesson/{lessonId}") { backStackEntry ->
                val lessonId = backStackEntry.arguments?.getString("lessonId") ?: ""
                LessonScreen(
                    languageId = lessonId, // Note: using lessonId now instead of languageId
                    onNavigateBack = { bottomNavController.popBackStack() }
                )
            }
        }
    }
}
