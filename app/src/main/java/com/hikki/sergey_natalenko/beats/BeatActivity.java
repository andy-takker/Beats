package com.hikki.sergey_natalenko.beats;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeatActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return BeatsFragment.newInstance();
    }
}
