package br.pro.lmit.androidjavaevents.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import br.pro.lmit.androidjavaevents.R;
import br.pro.lmit.androidjavaevents.model.CuponModel;
import br.pro.lmit.androidjavaevents.model.EventModel;

public class CuponRecyclerAdapter extends RecyclerView.Adapter<CuponRecyclerAdapter.CuponViewHolder> {

    private final LiveData<EventModel> mData;

    public CuponRecyclerAdapter(@NonNull LiveData<EventModel> data) {
        mData = data;
    }

    @NonNull
    @Override
    public CuponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_cupon_list_item, parent, false);
        return new CuponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuponViewHolder holder, int position) {
        if (mData.getValue() != null && mData.getValue().getCupons() != null) {
            holder.bind(mData.getValue().getCupons().get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mData.getValue() != null && mData.getValue().getCupons() != null) {
            return mData.getValue().getCupons().size();
        } else {
            return 0;
        }
    }

    class CuponViewHolder extends RecyclerView.ViewHolder {

        public CuponViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(CuponModel obj) {
            TextView text = itemView.findViewById(R.id.text);
            text.setText(String.valueOf(obj.getDiscount()));
        }
    }

}
