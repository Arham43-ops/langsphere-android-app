📘 LangSphere: The Comprehensive Developer Guide
================================================

This document is a detailed breakdown of the LangSphere Android application. It goes beyond the basics to explain **exactly** how the code communicates, how data flows, and how specific features were implemented line-by-line.

📑 Table of Contents
--------------------

1.  **Tech Stack Deep Dive**
    
2.  **Project Architecture (The MVVM Pattern)**
    
3.  **Database Schema & Data Flow**
    
4.  **Feature Breakdown (Code Level)**
    
    *   Auth System
        
    *   Lesson Engine
        
    *   AI Chatbot
        
    *   Gamification (XP & Leaderboard)
        
5.  **Navigation System**
    
6.  **Dependency Injection (Hilt)**
    

1\. 🛠️ Tech Stack Deep Dive
----------------------------

We chose specific libraries to solve specific problems. Here is the "Why" and "How" for each:

*   **Kotlin**: The language. We use "Coroutines" extensively for background tasks.
    
    *   _Why?_ It prevents the app from freezing when loading data.
        
*   **Jetpack Compose**: The UI toolkit.
    
    *   _Concept:_ "State Hoisting". We pass data **down** to buttons/cards, and events (clicks) go **up** to the ViewModel.
        
*   **Room Database**: An abstraction over SQLite.
    
    *   _Why?_ Writing raw SQL queries is error-prone. Room checks your queries at compile time (when you build the app), so you crash less.
        
*   **Hilt (Dagger)**:
    
    *   _Why?_ If LessonViewModel needs a Repository, and Repository needs a Database, creating this chain manually is hard. Hilt does it automatically using @Inject.
        

2\. 🏛️ Project Architecture: MVVM
----------------------------------

We strictly follow **Model-View-ViewModel**. This separates "Logic" from "Looks".

### The Flow of Data

1.  **User Trigger**: You click "Complete Lesson".
    
2.  **View Layer** (LessonScreen.kt): Calls viewModel.completeLesson().
    
3.  **ViewModel Layer** (LessonViewModel.kt): Launches a background process (Coroutine).
    
4.  **Repository Layer** (LessonRepository.kt): Decides _where_ the data goes (in this case, local DB).
    
5.  **Data Layer** (UserDao.kt): Runs the SQL command: UPDATE users SET totalXp = totalXp + 20.
    

### Why do we do this?

If we put database code inside the Button's onClick, the app would be efficient but **impossible to test** or change later. This separation allows us to swap the database for a cloud server later without changing the UI code!

3\. 💾 Database Schema & Data Flow
----------------------------------

Our database (

AppDatabase.kt) has two main tables (Entities):

### UserEntity Table

ColumnTypePurposeidStringUnique ID (UUID)nameStringUser's display nameemailStringLogin credentialtotalXpIntScore for rankingstreakIntDays active in a row

### AchievementEntity Table

ColumnTypePurposeidStringUnique IDtitleString"First Lesson", etc.isUnlockedBooleanTrue/False status

**Data Access Objects (DAOs):** These are interfaces (like 

UserDao) that define permitted operations:

*   @Query("SELECT \* FROM users"): Read
    
*   @Insert: Write
    
*   @Update: Modify
    

4\. 🔍 Feature Breakdown (Code Level)
-------------------------------------

### A. The Lesson Engine 📚

*   **Source:** LessonRepositoryImpl.kt contains hardcoded lesson data (Lists of Lesson objects).
    
*   **Display:** LessonListScreen gets this list and renders LessonCards.
    
*   **Logic:**
    
    *   **XP Calculation:** difficultyLevel \* 20. A level 2 lesson grants 40 XP.
        
    *   **Audio:** TextToSpeechManager handles the voice.
        
        *   _Wait Queue:_ If you click play before TTS loads, we added a "Waiting List" (pendingRequests) so the app doesn't crash. It plays the audio once ready.
            

### B. The AI Chatbot 🤖

Located in 

ConversationEngine.kt. It uses **Regex (Regular Expressions)** to find patterns.

*   **Input:** "How do I say hello in Spanish?"
    
*   **Logic:**
    
    1.  Detects keywords: "how", "say", "hello", "spanish".
        
    2.  Matches pattern: TranslationRequest.
        
    3.  Looks up dictionary: Hello -> Hola.
        
*   **Output:** "In Spanish, 'Hello' is 'Hola' 👋"
    

This is a "Rule-Based AI". It's simple but extremely fast and works offline.

### C. Gamification (XP & Leaderboard) 🏆

*   **XP:** When completeLesson() runs, we call userDao.addXp(userId, amount).
    
*   **Rank:** The **ViewModel** watches the user object.
    
    *   val rank = calculateRank(user.xp)
        
    *   The View updates automatically: "Beginner" -> "Novice" instantly.
        
*   **Leaderboard:** Uses a Flow that automatically sorts users.
    
    *   Query: ORDER BY totalXp DESC. The database does the sorting work for us!
        

5\. 🗺️ Navigation System
-------------------------

We use NavHost in 

MainScreen.kt. It works like a URL system on a website.

*   composable("home"): Shows HomeScreen
    
*   composable("lesson/{lessonId}"): Shows LessonScreen, expecting an ID.
    

When you click a lesson: navController.navigate("lesson/spanish\_basics\_1") The app matches this route and opens the correct screen with the correct data.

6\. 💉 Dependency Injection (Hilt)
----------------------------------

**The Problem:** 

LessonViewModel needs LessonRepository. LessonRepository needs AppDatabase. AppDatabase needs Context (Android System).

**The Solution (**

**AppModule.kt):** We write instructions called "Providers":

*   @Provides fun provideDatabase(...)
    
*   @Provides fun provideRepository(...)
    

Now, when we say @Inject constructor(repo: LessonRepository) in the ViewModel, Hilt automatically builds the database, builds the repo, and hands it to us. Magic! ✨

🏁 Conclusion
-------------

LangSphere is a robust application because it respects the "Separation of Concerns".

*   The **UI** only draws pixels.
    
*   The **ViewModels** make decisions.
    
*   The **Repositories** handle data.
    

This makes the app scalable. If you wanted to add a generic "Multiplayer Mode" later, you'd only need to touch the Repository and ViewModel; the UI could stay almost exactly the same!