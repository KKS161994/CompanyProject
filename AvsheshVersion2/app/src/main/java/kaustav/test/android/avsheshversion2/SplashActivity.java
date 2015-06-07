package kaustav.test.android.avsheshversion2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.json.JSONException;
import org.json.JSONObject;

import kaustav.test.android.avsheshversion2.data.PreferenceContract;

public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SharedPreferences status = getSharedPreferences(PreferenceContract.LoginStatus, Context.MODE_PRIVATE);

                    if (status.getBoolean(PreferenceContract.LoginStatus, false)) {
                        AsyncTask as = new AsyncTask() {
                            SharedPreferences mob_no = getSharedPreferences(PreferenceContract.MobileNumber, Context.MODE_PRIVATE);
                            SharedPreferences psswd = getSharedPreferences(PreferenceContract.Password, Context.MODE_PRIVATE);
                            String Mob = mob_no.getString(PreferenceContract.MobileNumber, null);
                            String Pass = psswd.getString(PreferenceContract.Password, null);
                            String out = null;

                            @Override
                            protected Object doInBackground(Object[] params) {

                                out = SignInActivity.loginStatus(Mob, Pass);
                                if (out == null) {
                                    SharedPreferences buyerstat = getSharedPreferences(PreferenceContract.BuyerStatus, Context.MODE_PRIVATE);
                                    Boolean bstat = buyerstat.getBoolean(PreferenceContract.BuyerStatus, true);
                                    if (bstat == true)
                                        startActivity(new Intent(SplashActivity.this, BuyerActivity.class));
                                    else
                                        startActivity(new Intent(SplashActivity.this, SellerActivity.class));
                                    finish();
                                } else {
                                    try {
                                        JSONObject servResult = new JSONObject(out);
                                        int resp = servResult.getInt("result");
                                        if (resp == 1) {
                                            SharedPreferences buyerstat = getSharedPreferences(PreferenceContract.BuyerStatus, Context.MODE_PRIVATE);
                                            Boolean bstat = buyerstat.getBoolean(PreferenceContract.BuyerStatus, true);
                                            if (bstat == true)
                                                startActivity(new Intent(SplashActivity.this, BuyerActivity.class));
                                            else
                                                startActivity(new Intent(SplashActivity.this, SellerActivity.class));
                                            finish();
                                        } else {
                                            startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                return null;
                            }
                        };
                        as.execute();
                    } else {
                        startActivity(new Intent(SplashActivity.this, SignUpOrInActivity.class)); // Not logged in : GOTO Sign Up or Sign In
                        finish();
                    }
                }
            }
        };
        timer.start();
    }
}
