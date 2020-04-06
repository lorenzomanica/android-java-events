package br.pro.lmit.androidjavaevents.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import br.pro.lmit.androidjavaevents.R;
import br.pro.lmit.androidjavaevents.model.EventModel;
import br.pro.lmit.androidjavaevents.model.PersonModel;

public class PeopleRecyclerAdapter extends RecyclerView.Adapter<PeopleRecyclerAdapter.PeopleViewHolder> {

    private final LiveData<EventModel> mData;

    public PeopleRecyclerAdapter(@NonNull LiveData<EventModel> data) {
        mData = data;
    }

    @NonNull
    @Override
    public PeopleRecyclerAdapter.PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_people_list_item, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleRecyclerAdapter.PeopleViewHolder holder, int position) {
        if (mData.getValue() != null && mData.getValue().getPeople() != null) {
            holder.bind(mData.getValue().getPeople().get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mData.getValue() != null && mData.getValue().getPeople() != null) {
            return mData.getValue().getPeople().size();
        } else {
            return 0;
        }
    }


    class PeopleViewHolder extends RecyclerView.ViewHolder {

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(PersonModel obj) {
            ImageView image = itemView.findViewById(R.id.image);
            image.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),
                    R.drawable.people_default));
        }
    }
}
