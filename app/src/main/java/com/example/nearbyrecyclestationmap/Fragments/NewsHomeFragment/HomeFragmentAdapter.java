package com.example.nearbyrecyclestationmap.Fragments.NewsHomeFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

//This is the fragment adapter for homepage
public class HomeFragmentAdapter extends FragmentStateAdapter {

    public HomeFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0)
            return new ActivityFragment(0);
        else if(position == 1)
            return new NewsFragment();
        else
            return new VideosFragment(0);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
