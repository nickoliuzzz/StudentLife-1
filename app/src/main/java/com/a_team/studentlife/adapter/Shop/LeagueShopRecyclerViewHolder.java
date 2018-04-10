package com.a_team.studentlife.adapter.Shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.Product;

public class LeagueShopRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView productNameTextView;
    private TextView productPriceTextView;
    private TextView isProductBoughtTextView;
    private LinearLayout productItem;


    public LeagueShopRecyclerViewHolder(View itemView) {
        super(itemView);
        this.productNameTextView = (TextView) itemView.findViewById(R.id.product_name);
        this.productPriceTextView = (TextView) itemView.findViewById(R.id.product_price);
        this.isProductBoughtTextView = (TextView) itemView.findViewById(R.id.is_product_bought);
        this.productItem = (LinearLayout) itemView.findViewById(R.id.product_card_linear_layout);
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
        setProductItemListener(product);
    }

    private void setProductItemListener(final Product product) {
        this.productItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), BuyProductActivity.class);
                intent.putExtra("productIndex", product.getProductId());
                intent.putExtra("productName", product.getProductName());
                intent.putExtra("productDescription", product.getProductDescription());
                intent.putExtra("productLeagueShopId", product.getLeagueShopId());
                intent.putExtra("productLeagueShopName", product.getLeagueShopName());
                intent.putExtra("productPrice", product.getProductPrice());
                intent.putExtra("productIsBought", product.isBought());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
