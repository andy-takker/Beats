package com.hikki.sergey_natalenko.beats;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SoundViewModelTest {
    private Beat mBeat;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeat = mock(Beat.class);
        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeat);
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle(){
        assertThat(mSubject.getTitle(), is(mSound.getName()));
    }
    @Test
    public void callsBeatPlayOnButtonClicked(){
        mSubject.onButtonClicked();
        verify(mBeat).play(mSound);
    }
}