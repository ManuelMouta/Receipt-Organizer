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

import com.example.manuel.receiptorganizer.R;

import java.io.File;
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
    public void onBindViewHolder(final ReceiptsListAdapter.ViewHolder holder, final int position) {
        final String receiptName = reciptList.get(position);
        final String finalReceiptName = receiptName.substring(0, receiptName.lastIndexOf(" "));
        holder.receiptName.setText(finalReceiptName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES) +
                        File.separator + "Receipts" +File.separator + receiptName)), "image/*");
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reciptList.size();
    }
}
