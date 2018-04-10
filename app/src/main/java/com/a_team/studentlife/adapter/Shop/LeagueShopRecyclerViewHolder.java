package com.a_team.studentlife.adapter.Shop;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.a_team.studentlife.R;

public class LeagueShopRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView productNameTextView;
    private TextView productPriceTextView;
    private TextView isProductBoughtTextView;


    public LeagueShopRecyclerViewHolder(View itemView) {
        super(itemView);
        this.productNameTextView = (TextView) itemView.findViewById(R.id.product_name);
        this.productPriceTextView = (TextView) itemView.findViewById(R.id.product_price);
        this.isProductBoughtTextView = (TextView) itemView.findViewById(R.id.is_product_bought);
    }

    @SuppressLint("SetTextI18n")
    public void bind(Product product) {
        this.productNameTextView.setText(product.getProductName());
        this.productPriceTextView.setText(productPriceTextView.getText() + " " + product.getProductPrice());
        if (product.isBought()) {
            this.isProductBoughtTextView.setText("Уже куплено");
            this.isProductBoughtTextView.setTextColor(Color.GREEN);
        } else {
            this.isProductBoughtTextView.setText("Еще не куплено");
            this.isProductBoughtTextView.setTextColor(Color.RED);
        }
    }
}
