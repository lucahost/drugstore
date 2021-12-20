# drugstore

Drugstore Android app. Developed during the course "Projektarbeit" at Fernfachhochschule Schweiz by Marc Bischof, Luca Hostettler, Sebastian Roethlisberger.

## Project Structure
| Package                         | Description                                                                       |
|---------------------------------|-----------------------------------------------------------------------------------|
| ch.ffhs.drugstore               | Root project containing the necessary dependencies and the entrypoint to the app. |
| ch.ffhs.drugstore.data          | Abstracts the access to external and local data sources.                          |
| ch.ffhs.drugstore.domain        | Contains the technology-independent business-logic.                               |
| ch.ffhs.drugstore.presenstation | Components visible to the user and minimal display logic.                         |
| ch.ffhs.drugstore.data.shared   | Contains components used by all layers of the system.                             |

The project structuring has been inspired by[The Clean Architecture by Robert C. Martin (Uncle Bob)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
and [Fernando Cejas](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/) (Apache License 2.0).

![CleanArchitecture](docs/CleanArchitecture.jpg)

## Development

It is highly recommended to use the latest version of [Android Studio](https://developer.android.com/studio/)
to interact with the application.

## Run

The application has been optimized and tested for the following device and system image.
Using devices with different configurations e.g. screen resolutions might not produce appropriate results.

| Parameter            | Value                        |
|----------------------|------------------------------|
| Device               | Pixel C                      |
| Size                 | 9.94"                        |
| Resolution           | 2560x1800                    |
| Density              | xhdpi                        |
| Orientation          | Landscape                    |
| Android Release Name | R                            |
| Android Version      | Android 11.0 (Google APIs)   |
| Android API Level    | 30                           |
| CPU/ABI              | Google APIs Intel Atom (x86) |
| Target               | Google APIs                  |

## Run E2E Tests

On your device, under Settings > Developer options, disable the following 3 settings:

* Window animation scale
* Transition animation scale
* Animator duration scale

To run the e2e tests within Android Studio complete the following steps:

* Add a new Android Tests configuration.
* Add a specific instrumentation runner: `androidx.test.runner.AndroidJUnitRunner`

To run the e2e tests from the command line execute the following Gradle command:

``` bash
./gradlew connectedAndroidTest
```
