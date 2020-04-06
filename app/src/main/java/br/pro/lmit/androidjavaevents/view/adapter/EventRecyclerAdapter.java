package br.pro.lmit.androidjavaevents.view.adapter;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.pro.lmit.androidjavaevents.R;
import br.pro.lmit.androidjavaevents.model.EventModel;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {

    private final LiveData<List<EventModel>> mData;

    private ItemEventClickListener mItemEventClickListener;

    public EventRecyclerAdapter(@NonNull LiveData<List<EventModel>> data) {
        mData = data;
    }

    public ItemEventClickListener getItemEventClickListener() {
        return mItemEventClickListener;
    }

    public void setItemEventClickListener(ItemEventClickListener l) {
        this.mItemEventClickListener = l;
    }

    @NonNull
    @Override
    public EventRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_event_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventRecyclerAdapter.ViewHolder holder, int position) {
        holder.bind(mData.getValue().get(position));
    }

    @Override
    public int getItemCount() {
        return mData.getValue().size();
    }


    public interface ItemEventClickListener {
        void onItemEventClick(String id);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(@NonNull final EventModel event) {
            TextView eventName = itemView.findViewById(R.id.event_name);
            eventName.setText(event.getTitle());

            TextView eventDate = itemView.findViewById(R.id.event_date);
            eventDate.setText(DateUtils.formatDateTime(itemView.getContext(), event.getDate(),
                    DateUtils.FORMAT_NUMERIC_DATE));

            TextView eventPrice = itemView.findViewById(R.id.event_price);
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
            eventPrice.setText(formatter.format(event.getPrice()));

            itemView.setOnClickListener(v -> {
                if (mItemEventClickListener != null) {
                    mItemEventClickListener.onItemEventClick(event.getId());
                }
            });

        }
    }
}
