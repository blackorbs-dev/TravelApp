## Travel App (blackorbs)

### How to Setup and Run the Project

Follow the steps below to set up and run this Android project:

#### Prerequisites
1. **Android Studio**: Ensure you have the latest stable version of [Android Studio](https://developer.android.com/studio) installed.
2. **Android SDK**: Verify that the Android SDK is installed and updated in Android Studio.
3. **Git**: Install Git to clone the repository. Download it from [git-scm.com](https://git-scm.com/).

#### Steps to Run the Project
1. **Clone the Repository**  
   Open a terminal and run the following command:  
   ````bash
   git clone https://github.com/blackorbs-dev/TravelApp.git
   ````
2. **Open the Project in Android Studio**
* Launch Android Studio.
* Go to **File > Open**.
* Navigate to the folder where you cloned the repository and select it.
* Click **OK** to open the project.

3. **Setup the Project**
* The project uses https://beeceptor.com/crud-api/ to handle api requests
* You should start by creating your api server from the page and add to this project
* Edit the **AppModule.kt** file and change the base url

_**app/src/main/java/blackorbs/dev/travelapp/dependency/AppModule.kt**_
```
@Singleton
    @Provides
    fun provideApiService(): ApiService =
        Retrofit.Builder()
            .baseUrl("https://cad0944d1ad035acb8af.free.beeceptor.com/") /*TODO: Change the api server base url*/
            .addCallAdapterFactory(RequestAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
            ))
            .build().create(ApiService::class.java)
```

4. **Run the Application**
* Connect an Android device via USB with Developer Options enabled, or set up an Android Virtual Device (AVD) in Android Studio.
* Click the **Run button** (green triangle) in the toolbar, or press **Shift + F10 or Control + R (macOS)**.
* Select your connected device or emulator.


