package com.example.manuel.receiptorganizer.utills;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manuel.receiptorganizer.MainActivity;
import com.example.manuel.receiptorganizer.R;
import com.example.manuel.receiptorganizer.activities.ReceiptInfoActivity;
import com.example.manuel.receiptorganizer.objects.CategoryObject;
import com.example.manuel.receiptorganizer.objects.ReceiptObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nb21910 on 22/05/16.
 */
public class ReceiptsListAdapter extends RecyclerView.Adapter<ReceiptsListAdapter.ViewHolder> {
    private List<ReceiptObject> receipts;
    private Context mContext;
    private String filter;
    private List<CategoryObject> categories;

    public ReceiptsListAdapter(Context ctx,String filter) {
        MainActivity.receiptDBoperation.open();
        this.mContext = ctx;
        this.filter = filter;
        this.receipts = filterData(filter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView receiptName;
        public TextView receiptTotal;
        public TextView receiptDate;
        public ImageView info_icon;
        public LinearLayout list_item_layout;
        public LinearLayout info_icon_layout;
        public TextView ctgTag;

        public ViewHolder(View view) {
            super(view);
            receiptName = (TextView) view.findViewById(R.id.receipt_name);
            receiptTotal = (TextView) view.findViewById(R.id.receipt_total);
            receiptDate = (TextView) view.findViewById(R.id.receipt_date);
            info_icon = (ImageView) view.findViewById(R.id.icon_info);
            list_item_layout = (LinearLayout) view.findViewById(R.id.list_item_layout);
            info_icon_layout = (LinearLayout) view.findViewById(R.id.info_icon_layout);
            ctgTag = (TextView) view.findViewById(R.id.calegorytag);
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
        final float receiptTotal = receipts.get(position).getTotal();
        final String receiptDate = receipts.get(position).getDate();

        holder.receiptName.setText(receiptName);
        holder.receiptTotal.setText(Float.toString(receiptTotal)+" €");
        holder.receiptDate.setText(receiptDate);

        holder.info_icon_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ReceiptInfoActivity.class);
                intent.putExtra("receiptInfo",receipts.get(position).getInfo());
                intent.putExtra("path",receipts.get(position).getPath());
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.list_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(receipts.get(position).getPath())), "image/*");
                holder.itemView.getContext().startActivity(intent);
            }
        });

        if(filter.equals("0")) {
            if (receipts.get(position).getCategory().equals("1")) {
                holder.ctgTag.setBackgroundColor(mContext.getResources().getColor(R.color.category1));
            } else if (receipts.get(position).getCategory().equals("2")) {
                holder.ctgTag.setBackgroundColor(mContext.getResources().getColor(R.color.category2));
            } else if (receipts.get(position).getCategory().equals("3")) {
                holder.ctgTag.setBackgroundColor(mContext.getResources().getColor(R.color.category3));
            } else if (receipts.get(position).getCategory().equals("4")) {
                holder.ctgTag.setBackgroundColor(mContext.getResources().getColor(R.color.category4));
            } else if (receipts.get(position).getCategory().equals("5")) {
                holder.ctgTag.setBackgroundColor(mContext.getResources().getColor(R.color.category5));
            }else if (receipts.get(position).getCategory().equals("6")) {
                holder.ctgTag.setBackgroundColor(mContext.getResources().getColor(R.color.category6));
            }
        }
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }

    private List<ReceiptObject> filterData(String filterCategoty){
        List<ReceiptObject> receipts = MainActivity.receiptDBoperation.getAllReceipts();
        List<ReceiptObject> receiptsFiltered = new ArrayList<>();
        int i =0;
        if(!filterCategoty.equals("0")) {
            for (i = 0; i < receipts.size(); i++) {
                if (receipts.get(i).getCategory().equals(filterCategoty))
                    receiptsFiltered.add(receipts.get(i));
            }
        }else{
            receiptsFiltered=receipts;
        }
        return receiptsFiltered;
    }


}
