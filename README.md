# Codeforces Mobile Android App

Codeforces Mobile Android application is an android app made in Kotlin that uses REST API’s provided by the [Codeforces](https://codeforces.com/apiHelp) website to view upcoming contests, profile statistics, rating changes, recent submissions, etc. This project uses all the advanced architecture components/jetpack components. 
This project was made under the WooTech 2021 Mentorship program.

## Team Members

1. <b>Mentor : </b> [Siddharth Gupta](https://github.com/itsSiddharthGupta) 
2. <b>Mentee : </b> [Khushboo Gupta](https://github.com/khushboogupta13)

## Overview of the App: 

The 2 main fragments are of the Upcoming Contests and the Profile Fragment. The Upcoming Contest Fragment shows the upcoming contests with the most reccent contest at the top. The profile fragment contains a card where the user can enter the codeforces username of the person whose progress he wants to track. The user can also view the rating changes and recent submissions. 

|<img src="https://i.imgur.com/moSjmxR.png" width="300"> |<img src="https://i.imgur.com/cj4I15B.png" width="300">|
| ------------------------------------------ | ----------------------------------------- |

After entering the username, the user can then navigate to the recent submissions and rating chnages fragment of the user whose username has been entered.

| <img src="https://i.imgur.com/r7cQEBw.png" width="300">|
| ------------------------------------------ |

In the Recent Submissions fragemnt, all the recent submissions of the user can be seen along with their verdict, runtime, language, number of test cases passed and the time of submission. In the Rating Changes fragment, all the rated contests in which the user has participated can be seen along with the rating change, rank and date of update of rating. 

|<img src="https://i.imgur.com/JlDXmwC.png" width="300"> |<img src="https://i.imgur.com/E40ZGix.png" width="300">|
| ------------------------------------------ | ----------------------------------------- |

## How do you use this repository?
Clone this repository<br>
```
https://github.com/khushboogupta13/codeforces-mobile-app
```

## Installing the prerequisites:

1. Download the Android IDE: Android Studio 
  a. Optional: Install the Android SDK: ``` brew install android-sdk ``` , Select the SDK that ```brew``` logged out back in the previous command
2. Open this project with in Android Studio IDE
3. The IDE will complain about *"Gradle sync failed".* Just follow what it says.
4. Once the IDE stops giving suggestions go to **Tools -> Android -> SDK Manager** and do what the SDK Manager says.
5. Once the SDK Manager stops giving suggestions, use it to install the Google Repository and the Android Support Repository.
