# 🌍 LangSphere

> **Master new languages effortlessly. Speak with confidence.**

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-blue.svg?style=flat&logo=android)](https://developer.android.com/jetpack/compose)
[![Hilt](https://img.shields.io/badge/DI-Hilt-orange.svg?style=flat&logo=google)](https://dagger.dev/hilt/)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg?style=flat&logo=android)](https://www.android.com/)

**LangSphere** is a cutting-edge, large-scale Android application designed to revolutionize language learning. Built with modern Android development standards (**Clean Architecture, MVVM, Jetpack Compose**), it combines AI-driven speech recognition with gamification to create an immersive learning experience.

---

## ✨ Key Features

### 🧠 **Immersive Learning**
*   **Speech-To-Text Verification**: Practice your pronunciation with real-time feedback using Android's Speech Recognizer.
*   **Native Text-To-Speech**: Listen to accurate phrasing in **English 🇺🇸, Italian 🇮🇹, Portuguese 🇧🇷, Russian 🇷🇺, and Mandarin 🇨🇳**.
*   **Diverse Content**: A growing library of lessons tailored for beginners and intermediates.

### 🎮 **Gamification & Engagement**
*   **Leaderboards 🏆**: Compete with friends and global users to reach the top ranks.
*   **Achievements 🌟**: Unlock badges like *"On Fire"*, *"Polyglot"*, and *"Scholar"* as you progress.
*   **Daily Goals 🎯**: Stay consistent with daily XP targets and streak tracking.
*   **Phrase of the Day 💡**: Get daily inspiration with new vocabulary.

### 🔐 **Seamless Experience**
*   **Auto-Login Persistence**: Jump straight into learning with `DataStore` session management.
*   **Premium UI/UX**: A custom **"Deep Teal & Coral"** theme with full Dark Mode support and smooth animations.
*   **Bottom Navigation**: Effortless switching between Learning, Ranking, and your Profile.

---

## 🛠️ Tech Stack & Architecture

LangSphere is built using industry-standard tools and a robust architecture:

*   **Language**: [Kotlin](https://kotlinlang.org/) (100%)
*   **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material Design 3)
*   **Architecture**: Clean Architecture + MVVM (Model-View-ViewModel)
*   **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)
*   **Asynchronous**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
*   **Navigation**: [Jetpack Navigation Compose](https://developer.android.com/guide/navigation/navigation-compose)
*   **Local Storage**: [DataStore Preferences](https://developer.android.com/topic/libraries/architecture/datastore) (Session Management)
*   **Hardware Integration**:
    *   `SpeechRecognizer` (Voice Input)
    *   `TextToSpeech` (Audio Output)

### Project Structure
```bash
com.example.langsphere
├── data                 # Data Layer (Repositories, Data Sources)
│   ├── local            # DataStore Implementation
│   └── repository       # Repository Implementations
├── domain               # Domain Layer (Business Logic)
│   ├── model            # Pure Kotlin Data Classes
│   └── repository       # Repository Interfaces
├── di                   # Hilt Dependency Injection Modules
└── presentation         # UI Layer (Screens, ViewModels)
    ├── auth             # Login & Registration
    ├── home             # Dashboard & Daily Goals
    ├── lesson           # Interactive Speech Lessons
    ├── profile          # User Stats & Settings
    ├── leaderboard      # Ranking System
    ├── welcome          # Landing/Onboarding
    └── theme            # Custom Design System
```

---

## � Getting Started

Follow these steps to run the project locally:

1.  **Clone the Repository**
    ```bash
    git clone https://github.com/Arham43-ops/langsphere-android-app.git
    cd langsphere-android-app
    ```

2.  **Open in Android Studio**
    *   Ensure you are using **Android Studio Koala** or newer.
    *   Let Gradle sync the dependencies.

3.  **Run the App**
    *   Select an emulator or connect a physical device.
    *   **Note**: For Speech Recognition features, a physical device or an emulator with microphone access is recommended.

---

## 🤝 Contributing

Contributions are welcome! If you have ideas for new languages or features:

1.  Fork the repository.
2.  Create your feature branch (`git checkout -b feature/AmazingFeature`).
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4.  Push to the branch (`git push origin feature/AmazingFeature`).
5.  Open a Pull Request.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<p align="center">
  Built with ❤️ by the <b>LangSphere Team</b>
</p>
