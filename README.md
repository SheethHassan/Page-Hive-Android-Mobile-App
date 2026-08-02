# Book Hive - Android Mobile App

A comprehensive Android application that enables users to search for books using the Google Books API and manage a personal reading library with cloud-based features.

## Overview

Book Hive is an Android app designed for book enthusiasts who want to:
- **Search Books**: Browse millions of books from the Google Books API
- **Manage Collections**: Build and organize your personal reading library
- **Cloud Sync**: Authenticate with Firebase and sync your library across devices
- **Track Reading**: Keep track of books you're reading, want to read, and have finished

## Features

- 📚 **Google Books API Integration**: Access a vast database of books with detailed information
- 🔐 **Firebase Authentication**: Secure user authentication with email and password
- ☁️ **Cloud Storage**: Sync your reading library across devices with Firebase Realtime Database
- 🔍 **Advanced Search**: Search books by title, author, and other filters
- ⭐ **Book Ratings & Reviews**: Rate and review books in your personal library
- 📖 **Reading Lists**: Create custom reading lists and organize books by categories
- 🎯 **User-Friendly Interface**: Intuitive and responsive UI for seamless navigation

## Tech Stack

- **Language**: Java
- **Platform**: Android
- **Database**: Firebase Realtime Database
- **Authentication**: Firebase Authentication
- **API**: Google Books API
- **Architecture**: MVC/MVVM (depending on implementation)

## Requirements

- Android Studio (Arctic Fox or later)
- Android SDK 21+ (API level 21)
- Java 11 or higher
- An active internet connection
- Google Play Services

## Installation

### Prerequisites

1. Clone the repository:
```bash
git clone https://github.com/SheethHassan/Page-Hive-Android-Mobile-App.git
cd Page-Hive-Android-Mobile-App
```

2. Open the project in Android Studio:
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. Set up Firebase:
   - Go to [Firebase Console](https://console.firebase.google.com/)
   - Create a new project or use an existing one
   - Add an Android app to your Firebase project
   - Download the `google-services.json` file
   - Place `google-services.json` in the `app/` directory

4. Configure Google Books API:
   - Go to [Google Cloud Console](https://console.cloud.google.com/)
   - Create a new project
   - Enable the Google Books API
   - Create an API key
   - Add the API key to your project configuration (in `gradle.properties` or `build.gradle`)

5. Sync Gradle:
   - Click "Sync Now" when prompted by Android Studio

### Build & Run

```bash
# Build the APK
./gradlew build

# Install and run on connected device or emulator
./gradlew installDebug
./gradlew run
```

## Project Structure

```
Page-Hive-Android-Mobile-App/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/                 # Java source code
│   │   │   ├── res/                  # Resources (layouts, drawables, strings)
│   │   │   └── AndroidManifest.xml   # App manifest
│   │   └── test/                     # Unit tests
│   ├── build.gradle                  # App-level Gradle build configuration
│   └── google-services.json          # Firebase configuration
├── gradle/
├── build.gradle                      # Project-level Gradle build configuration
├── settings.gradle                   # Gradle settings
└── README.md                         # This file
```

## API Documentation

### Google Books API
- **Documentation**: [Google Books API Reference](https://developers.google.com/books)
- **Base URL**: `https://www.googleapis.com/books/v1`
- **Key Endpoint**: `/volumes` - Search for books

### Firebase
- **Documentation**: [Firebase Documentation](https://firebase.google.com/docs)
- **Services Used**:
  - Authentication: User registration and login
  - Realtime Database: Store and sync user's reading library

## Usage

### First Time Setup

1. **Create an Account**: Launch the app and sign up with your email
2. **Search for Books**: Use the search functionality to find books by title or author
3. **Add to Library**: Tap any book to view details and add it to your personal library
4. **Manage Your Library**: View, rate, and organize your books from the library section

### Main Features

- **Search Screen**: Enter keywords to search the Google Books API
- **Book Details**: View comprehensive information about each book
- **My Library**: Access all your saved books and reading progress
- **User Profile**: Manage your account and preferences

## Configuration

### Environment Variables

Add the following to your `gradle.properties` or `local.properties`:

```properties
# Google Books API Key
GOOGLE_BOOKS_API_KEY=your_api_key_here

# Firebase Configuration (handled via google-services.json)
```

## Dependencies

Key dependencies used in this project:

- **Firebase**: `com.google.firebase:firebase-*`
- **Google Books API**: `com.squareup.retrofit2:*` (for API calls)
- **Material Design**: `com.google.android.material:material`
- **Networking**: `com.squareup.okhttp3:*`

See `build.gradle` for complete dependency list.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

Please ensure your code follows the project's coding standards and includes appropriate comments.

## Testing

To run unit tests:

```bash
./gradlew test
```

To run instrumented tests on a device:

```bash
./gradlew connectedAndroidTest
```

## Troubleshooting

### Common Issues

1. **Firebase Authentication Fails**
   - Ensure `google-services.json` is in the correct location (`app/`)
   - Verify Firebase project settings match your app configuration

2. **Google Books API Not Working**
   - Check that the API key is correctly configured
   - Verify the Google Books API is enabled in Google Cloud Console
   - Ensure internet permission is granted in `AndroidManifest.xml`

3. **Gradle Build Errors**
   - Run `./gradlew clean` followed by `./gradlew build`
   - Check that SDK version requirements are met
   - Ensure all dependencies are available

## Privacy & Security

- **User Data**: All user data is stored securely in Firebase
- **Authentication**: Passwords are handled securely by Firebase Authentication
- **API Keys**: Never commit API keys to version control; use build configurations
- **Privacy Policy**: Please see the in-app privacy policy for detailed information

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact & Support

- **Author**: [SheethHassan](https://github.com/SheethHassan)
- **Repository**: [Page-Hive-Android-Mobile-App](https://github.com/SheethHassan/Page-Hive-Android-Mobile-App)
- **Issues**: [Report a bug](https://github.com/SheethHassan/Page-Hive-Android-Mobile-App/issues)

## Acknowledgments

- [Google Books API](https://developers.google.com/books) for providing the book database
- [Firebase](https://firebase.google.com/) for backend services
- [Material Design](https://material.io/) for UI components and guidelines

---

**Happy reading! 📚**

For any questions or suggestions, feel free to open an issue or contact the maintainers.
