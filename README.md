# trendyol-internship

DATA LAYER : contains application data & business logic(deciding how application data must be created, stored, changed). 
data layer can be used on multiple screens, shared between different parts of the app & can be tested without UI.
The data layer is made of repositories that each can contain zero to many data sources. 
You should create a repository class for each different type of data you handle in your app.

DOMAIN LAYER : The domain layer is responsible for encapsulating complex business logic, or simple business logic that is reused by multiple ViewModels.
Should only be used to handle complexity or favor reusability. Classes in this layer are commonly called use cases or interactors. 
Each use case should have responsibility over a single functionality. 
For example, your app could have a GetTimeZoneUseCase class if multiple ViewModels rely on time zones to display the proper message on the screen.

PRESENTATION LAYER : The role of the UI layer (or presentation layer) is to display the application data on the screen. Whenever the data changes, either due to user interaction (such as pressing a button) or external input (such as a network response), the UI should update to reflect the changes.

The UI layer is made up of two things:

UI elements that render the data on the screen. You build these elements using Views or Jetpack Compose functions.
State holders (such as ViewModel classes) that hold data, expose it to the UI, and handle logic.