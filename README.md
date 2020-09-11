# Salat
![Alt text](/app/src/main/res/mipmap-xxhdpi/ic_launcher.png?raw=true )


## App Description
Salat App is and app that reminde you to send blessings to the prophit on Fridays via an mp3 file that plays as many times as you specify.
You can determine how many times the app should reminds you to pray, when is should start and when it should finish and the app will remind you automatically on each Friday.

## Project Structure
The project is designed using Mvp design pattern along with dependency injection using Dagger2.
The app schedules the prayers on Fridays through the implementation of AlarmManager that fires a background service where the mp3 track is played

It consists of
- 1 Activity
- 2 Fragments
- 1 Background service
- 2 Broadcast reciever



**MainActivity**

Is used to display the saved settings, host the fragments and most importantly start the alarm

**CounterFragment**

Is where you can choose how many times the mp3 track should plays

**TimePickerFragment**

Is where you can choose when the prayers should start and when they should stop

**AlarmReceiver**

Is a broadcast reciever to be started when the alarm fires

**PrayerService**

Is a background service where the prayers are played, it's started from the alarm receiver

**BootReciever**

Is a broadcast reciever to reschedule the alarm when the device reboot


## Special features in the app
- runs in the background, you can exit the app and that won't halt the preayers
- for effiecent battery usage, the background service is not running all the time, instead only is playing of Friday and self-stop at the end of Fridays

## Screenshots of the app

### MainActivity

![Alt text](/app/src/main/res/drawable/sc1.jpg?raw=true "MainActivity")


### CounterFragment

![Alt text](/app/src/main/res/drawable/sc2.jpg?raw=true "CounterFragment")

### TimePickerFragment

![Alt text](/app/src/main/res/drawable/sc3.jpg?raw=true "TimePickerFragment")
![Alt text](/app/src/main/res/drawable/sc4.jpg?raw=true "TimePickerFragment")

### The app in the running condition

![Alt text](/app/src/main/res/drawable/sc5.jpg?raw=true "MainActivity")
