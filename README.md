# Calorie Counter 🍎

Calorie Counter is an Android application for tracking daily food intake and physical activities.

The app allows users to search for foods and activities, view their nutritional information, record consumed food amounts and exercise duration, and track their daily calories and macronutrients.

## ✨ Features

- 🔎 Search for foods and physical activities
- 🍽️ Record consumed foods and their amounts
- 🔥 Calculate consumed calories
- 💪 Track protein, carbohydrates, and fat intake
- 🏃 Record physical activities and their duration
- 🔥 Calculate calories burned through activities
- 📊 Display daily calorie and macronutrient progress
- 🎯 Customize daily calorie and macronutrient goals
- 🌙 Support for light and dark themes
- 📝 View a list of recorded foods and activities
- 🗑️ Delete recorded items
- 💾 Persist data using Room Database

## 🛠️ Technologies

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Room Database**
- **SQLite**
- **Navigation 3**
- **MVVM**
- **Repository Pattern**

## 🏗️ Architecture

The application follows the **MVVM (Model–View–ViewModel)** architecture.

The main responsibilities are separated as follows:

- **View:** Responsible for displaying the UI and receiving user interactions.
- **ViewModel:** Manages UI state, application logic, and calculations.
- **Repository:** Acts as an abstraction layer between ViewModels and the database.
- **Model:** Contains data models and database-related components.

This structure helps keep the UI layer independent from business logic and database operations, making the project easier to maintain and extend.

## 🧭 Navigation

The application uses **Navigation 3** for navigation between screens.

The main screens include:

- Home
- Add Food
- Add Food Amount
- Add Activity
- Add Activity Duration

Navigation state is managed through a back stack, allowing users to move between screens and return to previous screens using the system back button.

## 🗄️ Database

**Room Database** is used for local data persistence.

It is built on top of SQLite and provides a structured way to store and retrieve application data.

The database is used to persist recorded foods and physical activities so that the data remains available after the application is closed.

## 📊 Food & Nutrition

Each food item contains nutritional information per 100 grams, including:

- Calories
- Protein
- Carbohydrates
- Fat

When the user enters the consumed amount, the application calculates the corresponding nutritional values based on the entered quantity.

## 🎯 Daily Goals

Users can customize their daily calorie and macronutrient goals.

The application allows users to adjust their targets for:

- Daily calories
- Protein
- Carbohydrates
- Fat

These goals are used to display daily progress and help users track their nutritional intake according to their personal targets.

## 🏃 Physical Activities

Each activity contains an estimated calorie expenditure per hour.

The application calculates the calories burned based on the selected activity and the duration entered by the user.

The burned calories are then included in the user's daily calorie balance.

## 🌙 Theme Support

The application supports both **Light Mode** and **Dark Mode**, providing a consistent user experience across different appearance preferences.

## 📱 Screenshots
<div align="center">
  <img width="163" height="351" alt="Home-Screen-Light" src="https://github.com/user-attachments/assets/b3aa4e63-18a1-4bc6-905c-99e1aae91e2c" />
  <img width="163" height="351" alt="Add-Menu-Light" src="https://github.com/user-attachments/assets/cd71e52f-0c41-4016-ab91-d66f739154e9" />
  <img width="163" height="351" alt="Add-Food-Light" src="https://github.com/user-attachments/assets/45a0d4b2-f386-4fba-a289-e275ffbc243f" />
  <img width="163" height="351" alt="Add-Exercise-Light" src="https://github.com/user-attachments/assets/65498791-2654-4073-9236-e70bac9cbe45" />
  <img width="163" height="351" alt="Food-Summary-Light" src="https://github.com/user-attachments/assets/2277eed6-c8b7-4521-8342-c0125e633d96" />
</div>
<div align="center">
  <img width="163" height="351" alt="Exercise-Summary-Light" src="https://github.com/user-attachments/assets/77439b46-87fd-471a-9cd2-ac8ca2075c50" />
  <img width="163" height="351" alt="Settings-Menu-Light" src="https://github.com/user-attachments/assets/6b8332b9-a1f4-4438-9b20-d0c193d54b3b" />
  <img width="163" height="351" alt="Home-Screen-Dark" src="https://github.com/user-attachments/assets/698f683f-dae7-4573-9ce7-70229e1590fb" />
  <img width="163" height="351" alt="Food-Summary-Dark" src="https://github.com/user-attachments/assets/c85328df-84b4-413c-ae89-75d2b7bcbc40" />
  <img width="163" height="351" alt="Add-Food-Dark" src="https://github.com/user-attachments/assets/08deaba5-7e67-4b4c-b4e4-04f8809d48fc" />
</div>

## 🎯 Project Goal

The main goal of this project is to develop a practical calorie-tracking application while applying modern Android development concepts such as Jetpack Compose, MVVM architecture, Room Database, and Navigation 3.

## 🚀 Future Development

Possible future improvements include:

- User profile and personalized calorie requirements
- More detailed nutritional information
- Daily and weekly statistics
- Charts and reports
- Barcode scanning
- Health Connect integration
- Meal and food category management

---

**Android Developer**

Kotlin • Jetpack Compose • Android
