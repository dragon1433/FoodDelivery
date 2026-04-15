package com.fooddelivery.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailItemAdapter extends RecyclerView.Adapter<OrderDetailItemAdapter.ViewHolder> {
    
    private List<OrderItem> items = new ArrayList<>();
    
    public void setItems(List<OrderItem> items) {
        this.items = items != null ? items : new ArrayList<>();
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_detail, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItem item = items.get(position);
        holder.bind(item);
    }
    
    @Override
    public int getItemCount() {
        return items.size();
    }
    
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDishName;
        TextView textQuantity;
        TextView textPrice;
        
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textDishName = itemView.findViewById(R.id.text_dish_name);
            textQuantity = itemView.findViewById(R.id.text_quantity);
            textPrice = itemView.findViewById(R.id.text_price);
        }
        
        void bind(OrderItem item) {
            textDishName.setText(item.getDishName());
            textQuantity.setText("x" + item.getQuantity());
            textPrice.setText(String.format("¥%.2f", item.getPrice() * item.getQuantity()));
        }
    }
}
