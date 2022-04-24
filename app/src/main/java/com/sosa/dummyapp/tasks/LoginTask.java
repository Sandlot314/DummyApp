package com.sosa.dummyapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.sosa.dummyapp.LoginFragment;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * This task takes care of executing a login action
 * It sends a request to the server which returns a status reflecting a successful login or failed login
 * Reference :  https://stackoverflow.com/questions/49234055/sending-post-request-in-android-studio
 */
public class LoginTask extends AsyncTask<String, String, String> {

    private static final String TAG = "LoginTask";
    private WeakReference<LoginFragment> loginFragmentWeakReference;
    private static String localhost; //emulator host loopback url
    private static final String LOGINENDPOINT = "/login";

    public LoginTask(LoginFragment frag){
        super();
        this.setContext(frag);
        localhost = "http://10.0.2.2:5000";
    }
    public void setContext(LoginFragment frag) {loginFragmentWeakReference = new WeakReference<>(frag);}

    @Override
    protected String doInBackground(String... strings) {
        Log.i(TAG, "Executing with params " + Arrays.toString(strings));

        String content = "";
        HttpURLConnection connection = null;
        boolean connected = false;
        try {
            URL url = new URL(localhost+LOGINENDPOINT);
            Log.i(TAG, url.toString());
            connection = (HttpURLConnection) url.openConnection();
            prepareConnection(connection);
            connection.connect();
            connected = true;

            int status = connection.getResponseCode();
            Log.i(TAG, "ResponseCode : " + status);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connected)
                connection.disconnect();
        }

        return "done";
    }

    private void prepareConnection(HttpURLConnection connection) throws IOException {
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`


    }
}
