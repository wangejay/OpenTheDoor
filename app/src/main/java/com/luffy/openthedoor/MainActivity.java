package com.luffy.openthedoor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.luffy.openthedoor.action.YunInterface;
import com.luffy.openthedoor.fragment.MainFragment;
import com.luffy.openthedoor.listener.OnSlideTriggerListener;

import retrofit.RestAdapter;
import retrofit.client.Response;


public class MainActivity extends Activity implements OnSlideTriggerListener {

    private static final String TAG = "OpenTheDoor";
    RestAdapter mRestAdapter;
    YunInterface mYunInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment mainFragment = new MainFragment();
        mainFragment.setOnSlideTriggerListener(this);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mainFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String location = new MyPreference(this).getLocation();
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint("http://" + location).build();

        mYunInterface = mRestAdapter.create(YunInterface.class);
    }

    @Override
    public void onSlideTrigger() {
        if (mYunInterface != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.d(TAG, "Try to trigger door open");
                        final Response response = mYunInterface.triggerDoorOpen();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.getStatus() == 200) {
                                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Log.d(TAG, "Response.getStatus isnt 200");
                                }

                            }
                        });
                    } catch (retrofit.RetrofitError e) {
                        Throwable cause = e.getCause();
                        String causeMessage = cause != null ? cause.toString() : "";
                        final String errorMsg = "error occurs " + causeMessage;
                        Log.e(TAG, errorMsg);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }
}
