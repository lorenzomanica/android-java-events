package br.pro.lmit.androidjavaevents.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.pro.lmit.androidjavaevents.R;
import br.pro.lmit.androidjavaevents.model.EventModel;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {

    private final LiveData<List<EventModel>> mData;

    public EventRecyclerAdapter(@NonNull LiveData<List<EventModel>> data) {
        mData = data;
    }

    @NonNull
    @Override
    public EventRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_event_list_item, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventRecyclerAdapter.ViewHolder holder, int position) {
        holder.bind(mData.getValue().get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(EventModel eventModel) {
            TextView eventName = itemView.findViewById(R.id.event_name);
            eventName.setText(eventModel.getName());
        }
    }
}
