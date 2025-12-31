LangSphere: Project Overview & Explanation 🌍
=============================================

Welcome to **LangSphere**! This document explains how this Android app was built from scratch, assuming no prior knowledge. Think of this as a "behind-the-scenes" tour of the code.

🏗️ The Foundation: What Tools Did We Use?
------------------------------------------

We used modern Android development tools to ensure the app is fast, reliable, and easy to update.

### 1. **Kotlin** (The Language)

Think of Kotlin as the language we speak to the phone. It's modern, safe, and concise. It's the official language for Android development today.

### 2. **Jetpack Compose** (The UI)

Used for building the **User Interface (UI)**—everything you see (buttons, text, images).

*   **Old way:** Using XML files (like HTML) to describe screens.
    
*   **Our way (Compose):** We write Kotlin code to _describe_ what the screen should look like. It's like building with LEGO blocks (Text, Button, Column, Row).
    

### 3. **Hilt** (Dependency Injection)

This sounds complex, but it's simple: **Hilt is like a delivery service.** Instead of every screen creating its own database connection or API client (which is messy and slow), Hilt creates them once and "injects" (delivers) them to whoever needs them. This keeps the code clean.

### 4. **Room** (The Database)

This is our local storage on the phone. It saves:

*   User info (Name, Email, XP)
    
*   Login state
    
*   Achievements It acts like a spreadsheet stored inside the private folder of the app.
    

### 5. **Coroutines & Flow** (Multitasking)

Apps need to do heavy work (loading data, saving files) without freezing the screen.

*   **Coroutines:** Allow the app to do tasks in the "background" while you keep scrolling.
    
*   **Flow:** Think of it as a pipe. Data flows through it, and whenever the data updates (e.g., you gain XP), the screen automatically updates to show the new number.
    

🏛️ The Architecture: MVVM
--------------------------

We used a pattern called **MVVM** (Model-View-ViewModel). Imagine a restaurant:

1.  **View (The Customer/Table):**
    
    *   This is the UI (Screens). It just shows food (data) and takes orders (clicks). It doesn't cook or manage inventory.
        
    *   _Examples:_ HomeScreen, LessonScreen, ProfileScreen.
        
2.  **ViewModel (The Waiter):**
    
    *   This sits between the Customer and the Kitchen. It takes orders ("User clicked login") and tells the Kitchen what to do. When the food is ready, it brings it to the table.
        
    *   It holds the state (e.g., "Is the user logged in?", "What is the current XP?").
        
    *   _Examples:_ HomeViewModel, LessonViewModel.
        
3.  **Model (The Kitchen/Pantry):**
    
    *   This is where the raw data lives. It fetches data from the Database (Room) or the Internet.
        
    *   _Examples:_ UserEntity, LessonRepository.
        

🚀 Key Features Explained
-------------------------

### 1. **Authentication (Login/Sign Up)**

*   **Function:** Lets users create an account.
    
*   **How it works:** We save the user's email and password (securely) in our **Room Database**. When you open the app later, we check if you're already logged in using a "DataStore" (a tiny file for simple settings).
    

### 2. **The Lesson System**

*   **Data:** We created a list of Lesson objects. Each lesson has a title ("Basics 1"), a language ("Spanish"), and a list of Phrases.
    
*   **The Screen:** LessonScreen shows these phrases in a list.
    
*   **Text-to-Speech (TTS):** To speak the words, we used Android's built-in **TextToSpeech** engine. We send it the text (e.g., "Hola") and the language code (Locale("es", "ES")), and the phone speaks it!
    
*   **Completion:** When you click "Complete Lesson", we calculate XP (Difficulty × 20) and tell the database to add it to your profile.
    

### 3. **AI Chat Assistant 🤖**

*   **Function:** A chatbot to practice languages.
    
*   **Secret:** It doesn't use a giant brain like ChatGPT (yet!). It uses **Pattern Matching**.
    
    *   If you type "Hello", it detects a greeting and replies "Hola" or "Bonjour" based on your language.
        
    *   If you type "Translate \[word\]", it looks for that pattern.
        
    *   This makes it fast and offline-friendly!
        

### 4. **Leaderboard & Profile**

*   **Profile:** Watches the User data in the database. If XP goes up, the "Rank" (Beginner, Expert) is recalculated instantly using a helper function.
    
*   **Leaderboard:** It asks the database: _"Give me all users, sorted by XP from highest to lowest."_ It then displays this list.
    

🗺️ Project Structure (Folders)
-------------------------------

If you look at the files on the left, here is what they mean:

*   data/: The raw data layer.
    
    *   local/: Database files (UserDao, AppDatabase).
        
    *   repository/: The logic that decides where to get data.
        
*   domain/: The "Business Logic". Pure Kotlin rules that don't care about Android.
    
    *   model/: Simple data classes like User, Lesson.
        
*   presentation/: The UI layer (what you see).
    
    *   theme/: Colors (Color.kt) and Fonts (Type.kt).
        
    *   home/, lesson/, profile/: Folders for each screen.
        
*   di/: Configuration for Hilt (Dependency Injection).
    

🎓 Summary
----------

**LangSphere** is a native Android app built with **Kotlin** and **Compose**. It follows clean coding practices (MVVM) to keep things organized. It uses a local database to save your progress and standard Android text-to-speech for audio. It's designed to be expandable—we can easily add more languages or features just by adding new data models!