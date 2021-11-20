package com.example.mangmacs.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangmacs.activities.DimsumListDetail;
import com.example.mangmacs.model.DimsumListModel;
import com.example.mangmacs.R;

import java.util.List;

public class DimsumAdapter extends RecyclerView.Adapter<DimsumAdapter.ProductViewHolder> {
    private Context context;
    private List<DimsumListModel> dimsumList;
    public DimsumAdapter(Context context, List<DimsumListModel> dimsumList){
        this.context = context;
        this.dimsumList = dimsumList;
    }
    @NonNull
    @Override
    public DimsumAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dimsum_list,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DimsumAdapter.ProductViewHolder holder, int position) {
        DimsumListModel dimsumListModel = dimsumList.get(position);
        Glide.with(context)
                .load(dimsumListModel.getImage())
                .into(holder.image);
        holder.textProductName.setText(dimsumListModel.getProductName());
        holder.textProductPrice.setText("₱ "+String.valueOf(dimsumListModel.getPrice()+".00"));
        holder.productContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DimsumListDetail.class);
                intent.putExtra("image",dimsumListModel.getImage());
                intent.putExtra("productName",dimsumListModel.getProductName());
                intent.putExtra("price",dimsumListModel.getPrice());
                intent.putExtra("status",dimsumListModel.getStatus());
                intent.putExtra("productVariation",dimsumListModel.getProductVariation());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dimsumList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textProductName,textProductPrice;
        CardView productContainer;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textProductName = itemView.findViewById(R.id.productName);
            textProductPrice = itemView.findViewById(R.id.productPrice);
            productContainer = itemView.findViewById(R.id.productContainer);
        }
    }
}
