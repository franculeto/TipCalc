package com.example.franc.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franc.tipcalc.R;
import com.example.franc.tipcalc.models.TipRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by franc on 11/2/2017.
 */

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder>
{
    private Context context;
    private List<TipRecord> dataset;
    private OnItemClickListener onItemClickListener;

    public TipAdapter(Context context, OnItemClickListener onItemClickListener){
        this.context = context;
        this.dataset = new ArrayList<TipRecord>();
        this.onItemClickListener = onItemClickListener;

    }

    public TipAdapter(Context context,List<TipRecord> dataset,  OnItemClickListener onItemClickListener){
        this.context = context;
        this.dataset = dataset;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord element = dataset.get(position);
        String strTip = String.format(context.getString(R.string.global_message_tip),
                element.getTip());
        String strTimeStamp = element.getDateFormatedd();
        holder.txtContent.setText(strTip);
        holder.txtItemTimeStamp.setText(strTimeStamp);
        holder.setOnItemClickListener(element, onItemClickListener);
    }

    public void add(TipRecord record)
    {
        dataset.add(0, record);
        notifyDataSetChanged();
    }

    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtContent)
        TextView txtContent;
        @BindView(R.id.txtItemTimeStamp)
        TextView txtItemTimeStamp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick( View v ) {
                    onItemClickListener.onItemClick(element);
                }
            });

        }
    }
}
