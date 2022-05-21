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
//This is Activities fragment + posters
public class ActivityFragment extends Fragment {
    RecyclerView rvAct;
    FirebaseDatabase database;
    Query reference;
    int account;
    public ActivityFragment(int account) {
        this.account = account;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_activity, container, false);
        rvAct = view.findViewById(R.id.rv_actFrag);
        rvAct.setHasFixedSize(false);
        rvAct.setLayoutManager(new LinearLayoutManager(getContext()));
        database = FirebaseDatabase.getInstance();
        //accType 0 = adult, 1 = child
        if(account==0)
            reference = database.getReference("activities").orderByChild("acctype").equalTo(0);
        else
            reference = database.getReference("activities").orderByChild("acctype").equalTo(1);
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<ActModelClass> options =
                new FirebaseRecyclerOptions.Builder<ActModelClass>().setQuery(reference, ActModelClass.class)
                        .build();
        FirebaseRecyclerAdapter<ActModelClass, HomeViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ActModelClass, HomeViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull HomeViewHolder holder, int position, @NonNull ActModelClass model) {
                        holder.setAct(requireActivity().getApplication(), model.getTitle(), model.getUrl(), model.getDescription());
                    }
                    @NonNull
                    @Override
                    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_activities_card, parent, false);
                        return new HomeViewHolder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        rvAct.setAdapter(firebaseRecyclerAdapter);
    }
}