package com.hikki.sergey_natalenko.beats;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hikki.sergey_natalenko.beats.databinding.FragmentBeatBinding;
import com.hikki.sergey_natalenko.beats.databinding.ListItemSoundBinding;

import java.util.List;

public class BeatsFragment extends Fragment {
    private static final int PORTRAIT_VALUE = 4;
    private static final int LANDSCAPE_VALUE = 6;
    private Beat mBeat;

    public static BeatsFragment newInstance(){
        return new BeatsFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeat = new Beat(getActivity());
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_beat,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.info:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("About program")
                        .setMessage("App: Beats\nAuthor: Sergey Natalenko\nJuly 2018 ©")
                        .setIcon(R.mipmap.beatbox_icon)
                        .setCancelable(false)
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        FragmentBeatBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat,container, false);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),PORTRAIT_VALUE));
        else
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),LANDSCAPE_VALUE));
        binding.recyclerView.setAdapter(new SoundAdapter(mBeat.getSounds()));
        return binding.getRoot();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mBeat.release();
    }

    private class SoundHolder extends RecyclerView.ViewHolder{
        private ListItemSoundBinding mBinding;

        private SoundHolder(ListItemSoundBinding binding){
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SoundViewModel(mBeat));
        }
        public void bind(Sound sound){
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sound> mSounds;
        public SoundAdapter(List<Sound> sounds){
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }
        @Override
        public void onBindViewHolder(SoundHolder holder, int position){
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }
        @Override
        public int getItemCount(){
            return mSounds.size();
        }
    }
}
