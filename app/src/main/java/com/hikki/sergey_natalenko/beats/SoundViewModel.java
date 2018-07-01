package com.hikki.sergey_natalenko.beats;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class SoundViewModel extends BaseObservable{
    private Sound mSound;
    private Beat mBeat;

    public SoundViewModel(Beat beat){
        mBeat = beat;
    }

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }
    @Bindable
    public String getTitle(){
        return  mSound.getName();
    }

    public void onButtonClicked() {
        mBeat.play(mSound);
    }
}
