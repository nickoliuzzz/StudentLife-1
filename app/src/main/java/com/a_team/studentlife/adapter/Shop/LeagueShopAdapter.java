package com.a_team.studentlife.adapter.Shop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.Product;

import java.util.ArrayList;
import java.util.List;

public class LeagueShopAdapter extends RecyclerView.Adapter<LeagueShopRecyclerViewHolder> {
    private ArrayList<Product> productsList = new ArrayList<>();

    public void addAllProducts(List<Product> items) {
        if (this.productsList.size() != 0)
            this.productsList.clear();
        int position = getItemCount();
        this.productsList.addAll(items);
        notifyItemRangeChanged(position, this.productsList.size());
    }

    @NonNull
    @Override
    public LeagueShopRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_item, parent, false);
        return new LeagueShopRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueShopRecyclerViewHolder holder, int position) {
        holder.bind(this.productsList.get(position));
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }
}
