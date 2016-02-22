![XXXHDPI Icon]

# Base Application

## Testing

Run all tests with `./gradlew test`.

# Api Server

## Run

Start application server with gradle: `./gradlew :api-server:run`.

## Deploy to [Heroku]

Build jar file: `./gradlew :api-server:jar`.

Deploy ([Heroku Toolbelt] should be installed): `heroku deploy:jar --jar api-server/build/libs/api-server-1.0.jar --app smart-quotes`.

Check logs with `heroku logs --app smart-quotes` and [web API].

# Android Application

## Testing

Run `./gradlew cAT` to execute UI android tests on device or emulator.

[XXXHDPI ICON]: https://raw.githubusercontent.com/dector/quotes/cb6c27ece6b16f1015e9dae9f712294abb863bcf/android/src/main/res/mipmap-xxxhdpi/ic_launcher.png
[Spek]: http://jetbrains.github.io/spek/
[Heroku]: https://heroku.com/
[Heroku Toolbelt]: https://devcenter.heroku.com/articles/getting-started-with-java#set-up
[web API]: http://smart-quotes.herokuapp.com/quotes