package labib.com.salatmvp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;

import javax.inject.Inject;

import labib.com.salatmvp.App;
import labib.com.salatmvp.data.DataManager;
import labib.com.salatmvp.di.service.DaggerServiceComponent;
import labib.com.salatmvp.di.service.ServiceComponent;
import labib.com.salatmvp.di.service.ServiceModule;

public class PrayerService extends Service implements MediaPlayer.OnPreparedListener {


    @Inject
    DataManager dataManager;

    @Inject
    MediaPlayer mediaPlayer;

    @Inject
    AudioManager audioManager;

    CountDownTimer countDownTimer;
    private long deltaMills;
    private int nOfPrayers;
    private long interval;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, PrayerService.class);
    }

    public static void start(Context context) {
        Intent starter = getStartIntent(context);
        context.startService(starter);
    }

    public static void stop(Context context) {
        context.stopService(getStartIntent(context));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .serviceModule(new ServiceModule(this))
                .build();
        component.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        deltaMills = getDataManager().getEndInMills() - System.currentTimeMillis();
        nOfPrayers = getDataManager().retrieveActiveNofPrayer();
        interval = deltaMills / nOfPrayers;

        mediaPlayer.setOnPreparedListener(this);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onPrepared(final MediaPlayer mediaPlayer) {

        countDownTimer = new CountDownTimer(deltaMills, interval) {

            @Override
            public void onTick(long l) {
                nOfPrayers = nOfPrayers - 1;
                getDataManager().updateActiveNoPrayers(nOfPrayers);
                if (audioManager.getRingerMode() != AudioManager.RINGER_MODE_SILENT && !audioManager.isMusicActive()) {
                    mediaPlayer.start();
                }
            }

            @Override
            public void onFinish() {
                stopSelf();
            }
        }
        .start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
        mediaPlayer.stop();
    }
}
