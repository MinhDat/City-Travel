package com.example.thaianhit.citytravel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    private LoadingDataTask task;
    AccountLocalStore accountLocalStore;
    @Bind(R.id.activity_splash)
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide status bar before setting content view
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        // Hide action bar
        getSupportActionBar().hide();
        Glide.with(SplashActivity.this).load(R.drawable.background2).asBitmap().into(new SimpleTarget<Bitmap>(400, 500) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    frameLayout.setBackground(drawable);
                }
            }
        });
        accountLocalStore = new AccountLocalStore(SplashActivity.this);
        task = new LoadingDataTask(this);
        task.execute();
    }

    @Override
    protected void onDestroy() {
        if (!task.isCancelled()) {
            task.cancel(true);
        }
        super.onDestroy();
    }

    private void gotoMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void gotoLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public boolean authenticate() {
        return accountLocalStore.GetUserLoogedIn();
    }
    private class LoadingDataTask extends AsyncTask<Void, Void, Boolean> {

        private WeakReference<SplashActivity> activityWeakReference;

        public LoadingDataTask(SplashActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            SplashActivity activity = activityWeakReference.get();
            activity.showProgressDialog();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            // Normally we would do some work here, like load data from server.
            // For our sample, we just sleep for 3 seconds.
            long endTime = System.currentTimeMillis() + 2*1000;
            boolean b = false;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {


                        b = authenticate();
                        wait(endTime - System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return b;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            SplashActivity activity = activityWeakReference.get();
            if (activity != null &&
                    !activity.isDestroyed() &&
                    !activity.isFinishing()) {

                activity.dismissProgressDialog();
                if(aBoolean == false) {
                    activity.gotoLoginScreen();
                }
                else
                {
                    activity.gotoMainScreen();
                }

            }
        }
    }
}
