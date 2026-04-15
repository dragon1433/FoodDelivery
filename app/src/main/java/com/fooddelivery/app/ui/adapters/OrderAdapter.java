package com.fooddelivery.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Order;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 订单列表适配器
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    
    private List<Order> orders = new ArrayList<>();
    private OnItemClickListener listener;
    private OnDeleteClickListener deleteListener;
    private OnReviewClickListener reviewListener;
    
    public interface OnItemClickListener {
        void onItemClick(Order order);
    }
    
    public interface OnDeleteClickListener {
        void onDeleteClick(Order order, int position);
    }
    
    public interface OnReviewClickListener {
        void onReviewClick(Order order, int position);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteListener = listener;
    }
    
    public void setOnReviewClickListener(OnReviewClickListener listener) {
        this.reviewListener = listener;
    }
    
    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind(order);
    }
    
    @Override
    public int getItemCount() {
        return orders.size();
    }
    
    class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView textRestaurantName;
        TextView textOrderStatus;
        TextView textItems;
        TextView textTotalPrice;
        TextView textOrderTime;
        android.widget.Button btnDeleteOrder;
        android.widget.Button btnReviewOrder;
        
        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textRestaurantName = itemView.findViewById(R.id.text_restaurant_name);
            textOrderStatus = itemView.findViewById(R.id.text_order_status);
            textItems = itemView.findViewById(R.id.text_items);
            textTotalPrice = itemView.findViewById(R.id.text_total_price);
            textOrderTime = itemView.findViewById(R.id.text_order_time);
            btnDeleteOrder = itemView.findViewById(R.id.btn_delete_order);
            btnReviewOrder = itemView.findViewById(R.id.btn_review_order);
        }
        
        void bind(Order order) {
            textRestaurantName.setText(order.getRestaurantName() != null ? order.getRestaurantName() : 
                itemView.getContext().getString(R.string.order_unknown_restaurant));
            textOrderStatus.setText(order.getStatus().getDisplayName());
            textTotalPrice.setText(String.format("¥%.2f", order.getTotalAmount()));
            
            // 格式化时间 - 处理空值
            if (order.getCreateTime() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
                String timeStr = sdf.format(order.getCreateTime());
                textOrderTime.setText(timeStr);
            } else {
                textOrderTime.setText(itemView.getContext().getString(R.string.order_unknown_time));
            }
            
            // 拼接商品信息
            if (order.getItems() != null && !order.getItems().isEmpty()) {
                StringBuilder itemsText = new StringBuilder();
                for (int i = 0; i < order.getItems().size(); i++) {
                    if (i > 0) itemsText.append(", ");
                    itemsText.append(order.getItems().get(i).getDishName())
                            .append(" x")
                            .append(order.getItems().get(i).getQuantity());
                }
                textItems.setText(itemsText.toString());
            } else {
                textItems.setText(itemView.getContext().getString(R.string.order_no_items));
            }
            
            // 点击事件
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(order);
                }
            });
            
            // 删除按钮点击事件
            btnDeleteOrder.setOnClickListener(v -> {
                if (deleteListener != null) {
                    deleteListener.onDeleteClick(order, getAdapterPosition());
                }
            });
            
            // 评价按钮点击事件
            btnReviewOrder.setOnClickListener(v -> {
                if (reviewListener != null) {
                    reviewListener.onReviewClick(order, getAdapterPosition());
                }
            });
            
            // 如果已经评价过，显示"已评价"
            if (order.getRating() > 0) {
                btnReviewOrder.setText("Reviewed");
                btnReviewOrder.setEnabled(false);
                btnReviewOrder.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#CCCCCC")));
            } else {
                btnReviewOrder.setText("Review");
                btnReviewOrder.setEnabled(true);
                btnReviewOrder.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#FF6B35")));
            }
        }
    }
}
