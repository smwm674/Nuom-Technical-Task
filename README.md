# Nuom-Technical-Task

## App Description
The project is developed using Android Studio IDE and the project is run on any Android device running Android 5.0 (API 21) or greater.
The application has a simple UI design. Main activity consits of three buttons and each button perform its functionality according to the description written in the functional requirment document.

The app uses libraries Hilt, Kotlin Coroutines, Retrofit, Flex Layout.

Hilt ot implemenet and demonstrate the connept of the depdency injection.

Kotlin Coroutines to implement the concept of concurrency and thread while making an API calls so that the Main UI thead wouldn't get blocked by the API Calls.

Retrofit used to make APi calls and manage the responses.

Flex Layout is used to auto adjust the grid layout columns so that layout doesn't looks awkard.

## Design pattern
MVVM design pattern used for the development of this application. 

Model–view–viewmodel (MVVM) is a software architectural pattern. 
MVVM facilitates a separation of development of the graphical user interface – be it via a markup language or GUI code – from development of the business logic or back-end logic (the data model ).

The primary purpose to use MVVM is that the code is broken up into classes with a small number of well defined responsibilities. 
One of main advantage of having code consisting of classes with a small number of well defined responsibilities is that it become easy to test the code.
MVVM allows us to test the code without awkward UI automation and interaction.
MVVM is ideal for simple and complex applications.
