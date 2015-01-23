package com.luffy.openthedoor.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.luffy.openthedoor.R;
import com.luffy.openthedoor.listener.OnSlideTriggerListener;

public class MainFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private SeekBar mSlideTrigger;
    private OnSlideTriggerListener mTriggerListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mSlideTrigger = (SeekBar) rootView.findViewById(R.id.slidebar);
        mSlideTrigger.setProgress(mSlideTrigger.getMax());
        mSlideTrigger.setOnSeekBarChangeListener(this);
        return rootView;
    }

    public void setOnSlideTriggerListener(OnSlideTriggerListener triggerListener) {
        mTriggerListener = triggerListener;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int max = seekBar.getMax();
        float progress = seekBar.getProgress() / max;

        if (progress < max / 20 && mTriggerListener != null) {
            mTriggerListener.onSlideTrigger();
        }
        mSlideTrigger.setProgress(mSlideTrigger.getMax());
    }
}
