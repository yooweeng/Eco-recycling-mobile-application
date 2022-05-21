package com.example.nearbyrecyclestationmap.Fragments.NewsHomeFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nearbyrecyclestationmap.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
//This is video fragment + videos
public class VideosFragment extends Fragment {
    RecyclerView rvPlayer;
    FirebaseDatabase database;
    Query reference;
    int account;
    public VideosFragment(int account) {
        this.account = account;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_videos, container, false);
        rvPlayer = view.findViewById(R.id.rv_videoFrag);
        rvPlayer.setHasFixedSize(true);
        rvPlayer.setLayoutManager(new LinearLayoutManager(getContext()));
        database = FirebaseDatabase.getInstance();
        //accType 0 = adult, 1 = child
        if(account==0)
            reference = database.getReference("videos").orderByChild("acctype").equalTo(0);
        else
            reference = database.getReference("videos").orderByChild("acctype").equalTo(1);
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<VideoModelClass> options =
                new FirebaseRecyclerOptions.Builder<VideoModelClass>().setQuery(reference, VideoModelClass.class)
                        .build();
        FirebaseRecyclerAdapter<VideoModelClass, HomeViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<VideoModelClass, HomeViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull HomeViewHolder holder, int position, @NonNull VideoModelClass model) {
                        holder.setVideo(requireActivity().getApplication(), model.getTitle(), model.getUrl(), model.getDescription());
                    }
                    @NonNull
                    @Override
                    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_video_card,parent, false);
                        return new HomeViewHolder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        rvPlayer.setAdapter(firebaseRecyclerAdapter);
    }
}