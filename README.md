# TMDB Movie App 🎬

An Android application built using TMDB APIs as part of a technical interview assignment.  
The app demonstrates clean architecture, pagination, search functionality, and detailed movie information.

---

## 📱 Features

- Movie list using TMDB Discover API
- Pagination using Paging 3
- Search movies by name with pagination
- Movie detail screen with:
    - Poster
    - Title
    - Release year
    - IMDb rating
    - play button
- Graceful handling of loading, empty, and error states

---

## 🏗 Architecture Overview

The application follows **MVVM architecture** with clear separation of concerns.



### Layers

- **UI Layer**
    - Activities & Fragments
    - XML layouts
    - ViewBinding

- **ViewModel Layer**
    - Manages UI state
    - Uses Kotlin Coroutines & Flow

- **Data Layer**
    - Repository pattern
    - Retrofit APIs
    - PagingSource for pagination

---

## 🧩 Libraries Used

- Kotlin
- MVVM Architecture
- Retrofit
- Paging 3
- Kotlin Coroutines & Flow
- Navigation Component
- ViewBinding
- Glide (Image loading)

---

## 🔑 API Setup

This project uses **TMDB API Read Access Token (v4)**.

### Steps to run the app:

1. Create an account at https://www.themoviedb.org
2. Generate a **Read Access Token (v4)**
3. Add the token to `local.properties`:

```properties
TMDB_API_KEY=YOUR_TMDB_BEARER_TOKEN
