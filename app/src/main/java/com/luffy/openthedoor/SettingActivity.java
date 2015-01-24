package com.luffy.openthedoor;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.lang.ref.WeakReference;

public class SettingActivity extends Activity {

    private MyPreference mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        mPreference = new MyPreference(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPreference.save();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private EditText mIPInput;
        private EditText mUsername;
        private EditText mPassword;
        WeakReference<SettingActivity> mSettingActivity;

        public PlaceholderFragment() {

        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            mSettingActivity = new WeakReference<SettingActivity>((SettingActivity)activity);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
            mIPInput = (EditText) rootView.findViewById(R.id.ip_dns_input);
            mIPInput.setText(mSettingActivity.get().mPreference.getLocation());
            mUsername = (EditText) rootView.findViewById(R.id.username_input);
            mUsername.setText(mSettingActivity.get().mPreference.getUsername());
            mPassword = (EditText) rootView.findViewById(R.id.password_input);
            mPassword.setText(mSettingActivity.get().mPreference.getPassword());
            return rootView;
        }

        @Override
        public void onPause() {
            super.onPause();
            SettingActivity activity = mSettingActivity.get();
            if (activity != null) {
                activity.mPreference.setLocation(mIPInput.getText().toString());
                activity.mPreference.setUsername(mUsername.getText().toString());
                activity.mPreference.setPassword(mPassword.getText().toString());
                activity.mPreference.setPassword(mPassword.getText().toString());
            }
        }
    }
}
