# NoteFlow
Modern offline-first Android Notes App built with Kotlin, Jetpack Compose, Room, MVVM, StateFlow, and Material 3.

## Features
- Add, edit, delete notes
- Title and description fields
- Search notes
- Pin important notes
- Created and updated dates
- Empty state screen
- Delete confirmation dialog
- Room local database
- Light and dark mode support
- Modern Material 3 UI

## Run Instructions
1. Open Android Studio.
2. Choose **File > Open** and select the `NoteFlow` folder.
3. Let Gradle sync finish.
4. Select an emulator or real Android device.
5. Click **Run**.

## Package Structure
- `model` - Room entity
- `data/local` - DAO and database
- `data/repository` - repository layer
- `di` - simple dependency provider
- `viewmodel` - StateFlow and app logic
- `ui/screens` - home and edit screens
- `ui/components` - reusable UI components
- `ui/navigation` - Navigation Compose routes
- `ui/theme` - Material 3 colors and theme


## Important: Fix Java/JDK Error
If Android Studio says it cannot find Java 17, open:
Preferences/Settings → Build, Execution, Deployment → Build Tools → Gradle
and set **Gradle JDK** to **Embedded JDK / Android Studio JBR**.
Then Sync, Clean, and Rebuild.

## App Icon
The NoteFlow launcher icon is already added in all mipmap folders and connected in AndroidManifest.xml.


## JDK 21 Fixed Version

This ZIP is configured for Java/JDK 21.

Android Studio setup:
1. Install JDK 21 if not already installed.
2. Open Android Studio > Settings/Preferences > Build, Execution, Deployment > Build Tools > Gradle.
3. Set Gradle JDK to JDK 21.
4. Click File > Sync Project with Gradle Files.
5. Build > Clean Project, then Build > Rebuild Project.

Important: Do not select JDK 25 or JDK 26 for Gradle 9.0.0. Use JDK 21.
