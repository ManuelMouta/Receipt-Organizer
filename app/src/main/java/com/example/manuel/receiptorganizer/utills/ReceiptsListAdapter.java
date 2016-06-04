package com.example.manuel.receiptorganizer.utills;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manuel.receiptorganizer.MainActivity;
import com.example.manuel.receiptorganizer.R;
import com.example.manuel.receiptorganizer.objects.ReceiptObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nb21910 on 22/05/16.
 */
public class ReceiptsListAdapter extends RecyclerView.Adapter<ReceiptsListAdapter.ViewHolder> {
    private List<ReceiptObject> receipts;

    public ReceiptsListAdapter() {
        MainActivity.receiptDBoperation.open();
        this.receipts = MainActivity.receiptDBoperation.getAllReceipts();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView receiptName;
        public TextView receiptTotal;

        public ViewHolder(View view) {
            super(view);
            receiptName = (TextView) view.findViewById(R.id.receipt_name);
            receiptTotal = (TextView) view.findViewById(R.id.receipt_total);
        }
    }

    @Override
    public ReceiptsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receipt_list_item, parent, false);

        return new ReceiptsListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReceiptsListAdapter.ViewHolder holder, final int position) {
        final String receiptName = receipts.get(position).getName();
        final int receiptTotal = receipts.get(position).getTotal();

        holder.receiptName.setText(receiptName);
        holder.receiptTotal.setText(Integer.toString(receiptTotal)+" €");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(receipts.get(position).getPath())), "image/*");
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }
}
