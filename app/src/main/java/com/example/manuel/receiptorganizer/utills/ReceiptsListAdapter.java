package com.example.manuel.receiptorganizer.utills;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manuel.receiptorganizer.R;

import java.util.ArrayList;

/**
 * Created by nb21910 on 22/05/16.
 */
public class ReceiptsListAdapter extends RecyclerView.Adapter<ReceiptsListAdapter.ViewHolder> {

    private ArrayList<String> reciptList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView receiptName;

        public ViewHolder(View view) {
            super(view);
            receiptName = (TextView) view.findViewById(R.id.receipt_name);
        }
    }

    public ReceiptsListAdapter(ArrayList<String> receipts) {
        this.reciptList = receipts;
    }

    @Override
    public ReceiptsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receipt_list_item, parent, false);

        return new ReceiptsListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReceiptsListAdapter.ViewHolder holder, int position) {
        String receiptName = reciptList.get(position);
        holder.receiptName.setText(receiptName);
    }

    @Override
    public int getItemCount() {
        return reciptList.size();
    }
}
