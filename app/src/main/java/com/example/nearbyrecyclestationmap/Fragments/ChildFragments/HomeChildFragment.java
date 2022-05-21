package com.example.nearbyrecyclestationmap.Fragments.ChildFragments;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.nearbyrecyclestationmap.R;
import com.example.nearbyrecyclestationmap.databinding.FragmentHomeChildBinding;
import com.example.nearbyrecyclestationmap.databinding.FragmentSettingsBinding;
import com.google.android.material.tabs.TabLayout;
public class HomeChildFragment extends Fragment {
    private FragmentHomeChildBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeChildBinding.inflate(inflater, container, false);
        tabLayout = binding.tlHomeChild;
        viewPager2 = binding.vp2HomeChild;
        tabLayout.addTab(tabLayout.newTab().setText("Images"));
        tabLayout.addTab(tabLayout.newTab().setText("Videos"));
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        ChildHomeFragmentAdapter adapter = new ChildHomeFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        return binding.getRoot();
    }
}