package com.example.nearbyrecyclestationmap.Fragments.NewsHomeFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbyrecyclestationmap.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// News fragment + API query to find topics
public class NewsFragment extends Fragment {

    String api="a168f150a4ab4f8b8ca25f9c503a0d32";
    String query = "environment AND recycle";
    ArrayList<NewsModelClass> newsModelClassArrayList;
    RecyclerView rvNews;

    RecyclerView.Adapter<HomeViewHolder> newsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_news, container, false);

        rvNews = view.findViewById(R.id.rv_newsFrag);
        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));

        newsModelClassArrayList = new ArrayList<>();
        findNews();

        // Inflate the layout for this fragment
        return view;
    }

    private void findNews() {
        NewsApiUtilities.getNewsApiInterface().getNews(query,5,api).enqueue(new Callback<NewsList>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<NewsList> call, @NonNull Response<NewsList> response) {
                if(response.isSuccessful()) {
                    newsModelClassArrayList.addAll(response.body().getArticles());
                    newsRecyclerView.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsList> call, Throwable t) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        newsRecyclerView =
                new RecyclerView.Adapter<HomeViewHolder>() {

                    @NonNull
                    @Override
                    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_news_card, parent, false);
                        return new HomeViewHolder(view);
                    }

                    @Override
                    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
                        holder.setNews(requireActivity().getApplication(), newsModelClassArrayList, position);
                    }

                    @Override
                    public int getItemCount() {
                        return newsModelClassArrayList.size();
                    }
                };
        rvNews.setAdapter(newsRecyclerView);
    }
}