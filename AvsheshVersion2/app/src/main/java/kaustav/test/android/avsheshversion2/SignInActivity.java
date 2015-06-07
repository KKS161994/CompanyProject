package kaustav.test.android.avsheshversion2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import kaustav.test.android.avsheshversion2.data.PreferenceContract;


public class SignInActivity extends ActionBarActivity {

    EditText ed1, ed2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);

        ed1 = (EditText)findViewById(R.id.mob_no);
        ed2 = (EditText)findViewById(R.id.passwd);
        b1 = (Button)findViewById(R.id.sign_in_btn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask as = new AsyncTask() {
                    String out = null;
                    String mob_no = ed1.getText().toString();
                    String passwd = ed2.getText().toString();

                    ProgressDialog dialog = new ProgressDialog(SignInActivity.this);

                    @Override
                    protected void onPreExecute() {
                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialog.setMessage("Signing in. Please wait...");
                        dialog.setIndeterminate(true);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        super.onPreExecute();
                    }

                    @Override
                    protected Object doInBackground(Object[] params) {
                        out = loginStatus(mob_no, passwd);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        if (out == null)
                        {
                            Toast.makeText(SignInActivity.this, "Problem with Connection", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            jsonHandle(out);
                        }
                        dialog.dismiss();
                        super.onPostExecute(o);
                    }
                };
                as.execute();
            }
        });
    }

    private int jsonHandle(String servRes)
    {
        try
        {
            JSONObject servResult = new JSONObject(servRes);

            int resp = servResult.getInt("result");

            if (resp == 1)
            {
                SharedPreferences mob_no = getSharedPreferences(PreferenceContract.MobileNumber, Context.MODE_PRIVATE);
                SharedPreferences psswd = getSharedPreferences(PreferenceContract.Password, Context.MODE_PRIVATE);
                SharedPreferences status = getSharedPreferences(PreferenceContract.LoginStatus, Context.MODE_PRIVATE);
                SharedPreferences usrid = getSharedPreferences(PreferenceContract.UserID, Context.MODE_PRIVATE);
                SharedPreferences buyerstat = getSharedPreferences(PreferenceContract.BuyerStatus, Context.MODE_PRIVATE);

                int userid = servResult.getInt("userid");
                int buyer = servResult.getInt("buyer");
                int seller = servResult.getInt("seller");

                mob_no.edit().putString(PreferenceContract.MobileNumber, ed1.getText().toString()).commit();
                psswd.edit().putString(PreferenceContract.Password, ed2.getText().toString()).commit();
                usrid.edit().putInt(PreferenceContract.UserID, userid).commit();
                status.edit().putBoolean(PreferenceContract.LoginStatus, true).commit();

                Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                if (buyer == 1)
                {
                    buyerstat.edit().putBoolean(PreferenceContract.BuyerStatus, true).commit();
                    startActivity(new Intent(SignInActivity.this, BuyerActivity.class));
                    finish();
                    SignUpOrInActivity.fa.finish();
                }
                if (seller == 1)
                {
                    buyerstat.edit().putBoolean(PreferenceContract.BuyerStatus, false).commit();
                    startActivity(new Intent(SignInActivity.this, SellerActivity.class));
                    finish();
                    SignUpOrInActivity.fa.finish();
                }
            }
            else if (resp == 0)
            {
                ed2.setText("");
                Toast.makeText(SignInActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            }
            else if (resp == 2)
            {
                ed2.setText("");
                Toast.makeText(SignInActivity.this, "User doesn't exist. Sign Up first", Toast.LENGTH_SHORT).show();
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    public static String loginStatus(String Mob, String Pass)
    {
        String jsonResponse = "";

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String data = null;
        try
        {
            final String MOB_QUERY = "mob";
            final String PASS_QUERY = "pass";
            data = URLEncoder.encode(MOB_QUERY, "UTF-8") + "=" + URLEncoder.encode(Mob, "UTF-8");
            data = data + "&" + URLEncoder.encode(PASS_QUERY, "UTF-8") + "=" + URLEncoder.encode(Pass, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }

        URL url = null;
        try
        {
            url = new URL("http://avshesh.co.in/apptest/jsontest.php");
        }
        catch (MalformedURLException e)
        {
            return null;
        }

        try
        {
            urlConnection = (HttpURLConnection) url.openConnection();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try
        {
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
        }
        catch (ProtocolException e)
        {
            return null;
        }

        try
        {
            urlConnection.connect();
            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonResponse = buffer.toString();
            return jsonResponse;
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
