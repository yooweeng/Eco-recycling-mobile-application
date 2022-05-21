package com.example.nearbyrecyclestationmap.Fragments.NewsHomeFragment;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nearbyrecyclestationmap.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//This is to set content of each fragment (activity, news & video)
public class HomeViewHolder extends RecyclerView.ViewHolder {

    View viewHolder;

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        viewHolder = itemView;
    }

    public void setVideo(final Application ctx, String title, final String url, String description) {
        SimpleExoPlayer exoPlayer;
        PlayerView vidPlayer;
        TextView vidTitle = viewHolder.findViewById(R.id.tv_VideoTitle);
        TextView vidDes = viewHolder.findViewById(R.id.tv_VideoDescription);
        vidPlayer = viewHolder.findViewById(R.id.ep_VideoView);

        vidTitle.setText(title);
        vidDes.setText(description);
        try {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(ctx);
            Uri video = Uri.parse(url);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(video,dataSourceFactory,extractorsFactory,null,null);
            vidPlayer.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);
        } catch (Exception e) {
            Log.e("VideoHolder", "ExoPlayer error " + e.toString());
        }
    }

    public void setAct(final Application ctx, String title, final String url, String description) {
        TextView actTitle = viewHolder.findViewById(R.id.tv_ActivityTitle);
        TextView actDesc = viewHolder.findViewById(R.id.tv_ActivityDescription);
        ImageView actPoster = viewHolder.findViewById(R.id.iv_ActivityPoster);

        actTitle.setText(title);
        actDesc.setText(description);

        Picasso.get().load(url).into(actPoster);
    }


    @SuppressLint("SetTextI18n")
    public void setNews(Application ctx, ArrayList<NewsModelClass> newsModelClassArrayList, int position) {
        TextView newsTitle = viewHolder.findViewById(R.id.tv_newsTitle);
        TextView newsContent = viewHolder.findViewById(R.id.tv_newsContent);
        TextView newsAuthor = viewHolder.findViewById(R.id.tv_newsAuthor);
        TextView newsDate = viewHolder.findViewById(R.id.tv_newsTime);
        ImageView newsThumbnail = viewHolder.findViewById(R.id.iv_newsThumbnail);
        CardView cardView = viewHolder.findViewById(R.id.cv_newsCard);


        cardView.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, NewsWebView.class);
            intent.putExtra("url", newsModelClassArrayList.get(position).getUrl());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intent);
        });
        newsTitle.setText(newsModelClassArrayList.get(position).getTitle());
        newsContent.setText(newsModelClassArrayList.get(position).getDescription());
        newsAuthor.setText(newsModelClassArrayList.get(position).getAuthor());
        newsDate.setText("Published At:-" + newsModelClassArrayList.get(position).getPublishedAt());
        Glide.with(ctx).load(newsModelClassArrayList.get(position).getUrlToImage()).into(newsThumbnail);
    }
}
