package com.bigr.applestore;

import com.firebase.client.Firebase;

/**
 * Created by Rana on 3/18/2016.
 */
public class AppleLuranApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }

}