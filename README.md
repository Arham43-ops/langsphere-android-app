# LangSphere 🌍 - Advanced Language Learning App

LangSphere is a modern, large-scale Android application designed to make language learning effortless and engaging. Built with **Jetpack Compose** and **Clean Architecture**, it features voice recognition, gamification, and a premium design.

## 🚀 Features

### 🌟 Core Experience
*   **Multi-Language Support**: Learn English 🇺🇸, Italian 🇮🇹, Portuguese 🇧🇷, Russian 🇷🇺, and Mandarin 🇨🇳.
*   **Interactive Lessons**: Speak to learn! Uses **Speech-To-Text** methodology to verify your pronunciation.
*   **Text-To-Speech**: Hear native-like pronunciation for every phrase.

### 🔐 Authentication & Persistence (Large Scale)
*   **Secure Auth**: Full Login and Registration flow (Mock-backend simulation).
*   **Auto-Login**: Remembers your session using `DataStore`. Open the app and jump right back in!
*   **Splash Screen**: Seamless startup experience.

### 🎮 Gamification & Creativity
*   **Leaderboard**: Compete with other students and see your rank 🏆.
*   **Achievements**: Earn badges like "On Fire" 🔥 and "Polyglot" for your progress.
*   **Daily Goals**: Track your daily XP and stay motivated.
*   **Phrase of the Day**: Get inspired every day with a new quote.

### 🎨 Design & UX
*   **Premium Theme**: Custom "Deep Teal & Coral" color palette with full Dark Mode support.
*   **Bottom Navigation**: intuitive navigation between Home, Leaderboard, and Profile.
*   **Profile Page**: Detailed user stats, streak tracking, and settings.

## 🛠 Tech Stack

*   **Language**: Kotlin
*   **UI Toolkit**: Jetpack Compose (Material3)
*   **Architecture**: MVVM + Clean Architecture (Presentation, Domain, Data layers)
*   **Dependency Injection**: Hilt (Dagger)
*   **Network**: Retrofit & OkHttp
*   **Local Storage**: DataStore Preferences (Session management)
*   **Navigation**: Jetpack Navigation Compose
*   **Speech**: Android SpeechRecognizer & TextToSpeech APIs

## 📂 Project Structure

```
com.example.langsphere
├── data
│   ├── local         # DataStore, Local persistence
│   └── repository    # Repository Implementations (Auth, Lesson)
├── domain
│   ├── model         # Data classes (User, Lesson, Language)
│   └── repository    # Repository Interfaces
├── di                # Hilt Modules
└── presentation
    ├── auth          # Login, Register, AuthViewModel
    ├── home          # HomeScreen, HomeViewModel
    ├── lesson        # Interactive LessonScreen (Speech logic)
    ├── profile       # ProfileScreen, Stats
    ├── leaderboard   # LeaderboardScreen
    ├── achievements  # AchievementsScreen
    ├── welcome       # Landing Page
    ├── splash        # Startup Logic
    ├── navigation    # MainScreen, BottomNav, AppNavigation
    └── theme         # Custom Color/Type/Theme
```

## 📦 Setup & Installation

1.  Clone the repository:
    ```bash
    git clone https://github.com/Arham43-ops/langsphere-android-app.git
    ```
2.  Open in **Android Studio**.
3.  Sync Gradle dependencies.
4.  Run on an Emulator or Device (Microphone permission required for Speech lessons).

## 🔮 Future Roadmap
*   Real Backend implementation (Firebase/Ktor).
*   Social features (Friend lists).
*   Space Repetition System (SRS) for vocabulary.

---
*Built with ❤️ by the LangSphere Team*
