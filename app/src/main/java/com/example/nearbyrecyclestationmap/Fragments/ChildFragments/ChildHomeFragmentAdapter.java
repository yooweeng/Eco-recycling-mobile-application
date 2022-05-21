package com.example.nearbyrecyclestationmap.Fragments.ChildFragments;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.nearbyrecyclestationmap.Fragments.NewsHomeFragment.ActivityFragment;
import com.example.nearbyrecyclestationmap.Fragments.NewsHomeFragment.VideosFragment;

public class ChildHomeFragmentAdapter extends FragmentStateAdapter {
    public ChildHomeFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0)
            return new ActivityFragment(1);
        else
            return new VideosFragment(1);
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}