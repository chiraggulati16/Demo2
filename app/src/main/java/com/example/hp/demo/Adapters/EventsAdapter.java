package com.example.hp.demo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hp.demo.Intents.Proof;
import com.example.hp.demo.Intents.Source;
import com.example.hp.demo.Model.EventsItem;
import com.example.hp.demo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsHolder> {

    private String img_url="https://d235dzzkn2ryki.cloudfront.net/";
    private Context context;
    List<EventsItem> eventsItems;
    public static final String Source_Url="sourece_url";
    public static final String Proof_Url="proof_url";

    public EventsAdapter(Context context, List<EventsItem> eventsItems) {
        this.context = context;
        this.eventsItems = eventsItems;
    }


    @NonNull
    @Override
    public EventsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_events,parent,false);
        return new EventsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsHolder holder, int position) {

        final EventsItem eventsList=eventsItems.get(position);

        Picasso.get().load(new String(img_url).concat(eventsList.getId()).concat("_large.png")).into(holder.imgView);
        holder.name.setText(eventsList.getName().concat("("+eventsList.getmSymbol()+")"));
        holder.date.setText(eventsList.getmDate());
        holder.title.setText(eventsList.getTitle());
        holder.desc.setText(eventsList.getDescription());
        holder.percent.setText(String.valueOf(eventsList.getPercentage()).concat("%"));
        holder.vote.setText(String.valueOf(eventsList.getVoteCount()).concat(" votes"));
        holder.editDate.setText("Added Date ".concat(eventsList.getCreateDate()));
        holder.progress.setProgress(eventsList.getPercentage());
        holder.source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Source.class);
                intent.putExtra(Source_Url, eventsList.getSource());
                context.startActivity(intent);
            }
        });
        holder.proof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Proof.class);
                intent.putExtra(Proof_Url,eventsList.getProof());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsItems.size();
    }

    public class EventsHolder extends RecyclerView.ViewHolder{

        ImageView imgView;
        TextView name;
        TextView date;
        TextView title;
        TextView desc;
        Button proof;
        Button source;
        TextView percent;
        TextView vote;
        ProgressBar progress;
        TextView editDate;

        public EventsHolder(View itemView) {
            super(itemView);

            imgView=itemView.findViewById(R.id.symbolimg);
            name=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);
            title=itemView.findViewById(R.id.titleName);
            desc=itemView.findViewById(R.id.desc);
            proof=itemView.findViewById(R.id.proof);
            source=itemView.findViewById(R.id.source);
            percent=itemView.findViewById(R.id.percent);
            vote=itemView.findViewById(R.id.votes);
            progress=itemView.findViewById(R.id.progress);
            editDate=itemView.findViewById(R.id.editDate);

        }
    }
}
