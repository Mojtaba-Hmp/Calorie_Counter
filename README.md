# Calorie Counter 🍎

Calorie Counter is an Android application for tracking daily food intake and physical activities.

The app allows users to search for foods and activities, view their nutritional information, record consumed food amounts and exercise duration, and track their daily calories and macronutrients.

## ✨ Features

* 🔎 Search for foods and physical activities
* 🍽️ Record consumed foods and their amounts
* 🔥 Calculate consumed calories
* 💪 Track protein, carbohydrates, and fat intake
* 🏃 Record physical activities and their duration
* 🔥 Calculate calories burned through activities
* 📊 Display daily calorie and macronutrient progress
* 📝 View a list of recorded foods and activities
* 🗑️ Delete recorded items
* 💾 Persist data using Room Database

## 🛠️ Technologies

* **Kotlin**
* **Jetpack Compose**
* **Material 3**
* **Room Database**
* **SQLite**
* **Navigation 3**
* **MVVM**
* **Repository Pattern**

## 🏗️ Architecture

The application follows the **MVVM (Model–View–ViewModel)** architecture.

The main responsibilities are separated as follows:

* **View:** Responsible for displaying the UI and receiving user interactions.
* **ViewModel:** Manages UI state, application logic, and calculations.
* **Repository:** Acts as an abstraction layer between ViewModels and the database.
* **Model:** Contains data models and database-related components.

This structure helps keep the UI layer independent from business logic and database operations, making the project easier to maintain and extend.

## 🧭 Navigation

The application uses **Navigation 3** for navigation between screens.

The main screens include:

* Home
* Add Food
* Add Food Amount
* Add Activity
* Add Activity Duration

Navigation state is managed through a back stack, allowing users to move between screens and return to previous screens using the system back button.

## 🗄️ Database

**Room Database** is used for local data persistence.

It is built on top of SQLite and provides a structured way to store and retrieve application data.

The database is used to persist recorded foods and physical activities so that the data remains available after the application is closed.

## 📊 Food & Nutrition

Each food item contains nutritional information per 100 grams, including:

* Calories
* Protein
* Carbohydrates
* Fat

When the user enters the consumed amount, the application calculates the corresponding nutritional values based on the entered quantity.

## 🏃 Physical Activities

Each activity contains an estimated calorie expenditure per hour.

The application calculates the calories burned based on the selected activity and the duration entered by the user.

The burned calories are then included in the user's daily calorie balance.

## 📱 Screenshots
<div align="center">
<img width="220" height="474" alt="Screenshot_2026-07-18-10-34-49-227_com mj caloriecounter-edit" src="https://github.com/user-attachments/assets/c9fb8a64-5775-4e25-bcb3-1f1630faf6f3" />
<img width="220" height="474" alt="Screenshot_2026-07-18-10-37-59-553_com mj caloriecounter-edit" src="https://github.com/user-attachments/assets/eeb4907b-3db0-49a2-80f0-6224b3fd1745" />
<img width="220" height="474" alt="Screenshot_2026-07-18-10-36-30-496_com mj caloriecounter-edit" src="https://github.com/user-attachments/assets/75b1c318-900e-4cb0-826a-6ae540bfd1cf" />
</div>
<div align="center">
<img width="220" height="474" alt="Screenshot_2026-07-18-10-40-02-526_com mj caloriecounter-edit" src="https://github.com/user-attachments/assets/521c5137-0b04-4721-9468-6fb1f3c52f80" />
<img width="220" height="474" alt="Screenshot_2026-07-18-10-38-34-225_com mj caloriecounter-edit" src="https://github.com/user-attachments/assets/3274fed7-559e-41c6-b161-de763e270ecc" />
<img width="220" height="474" alt="Screenshot_2026-07-18-10-39-23-224_com mj caloriecounter-edit" src="https://github.com/user-attachments/assets/7e05a579-e98f-40aa-a960-8d47830029f4" />
</div>

## 🎯 Project Goal

The main goal of this project is to develop a practical calorie-tracking application while applying modern Android development concepts such as Jetpack Compose, MVVM architecture, Room Database, and Navigation 3.

## 🚀 Future Development

Possible future improvements include:

* User profile and personal calorie requirements
* More detailed nutritional information
* Daily and weekly statistics
* Charts and reports
* Barcode scanning
* Health Connect integration
* Meal and food category management
Android Developer
Kotlin • Jetpack Compose • Android
