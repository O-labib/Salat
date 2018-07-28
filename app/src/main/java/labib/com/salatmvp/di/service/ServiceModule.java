package labib.com.salatmvp.di.service;


import android.app.AlarmManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import dagger.Module;
import dagger.Provides;
import labib.com.salatmvp.R;

@Module
public class ServiceModule {

    private Context context;

    public ServiceModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerService
    MediaPlayer provideMediaPlayer() {
        return MediaPlayer.create(context, R.raw.sallo);
    }

    @Provides
    @PerService
    AudioManager provideAudioManager(){
        return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }
}
