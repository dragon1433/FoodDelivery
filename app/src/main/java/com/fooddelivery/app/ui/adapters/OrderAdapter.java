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
    
    public interface OnItemClickListener {
        void onItemClick(Order order);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
        
        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textRestaurantName = itemView.findViewById(R.id.text_restaurant_name);
            textOrderStatus = itemView.findViewById(R.id.text_order_status);
            textItems = itemView.findViewById(R.id.text_items);
            textTotalPrice = itemView.findViewById(R.id.text_total_price);
            textOrderTime = itemView.findViewById(R.id.text_order_time);
        }
        
        void bind(Order order) {
            textRestaurantName.setText(order.getRestaurantName() != null ? order.getRestaurantName() : "未知餐厅");
            textOrderStatus.setText(order.getStatus().getDisplayName());
            textTotalPrice.setText(String.format("¥%.2f", order.getTotalAmount()));
            
            // 格式化时间 - 处理空值
            if (order.getCreateTime() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
                String timeStr = sdf.format(order.getCreateTime());
                textOrderTime.setText(timeStr);
            } else {
                textOrderTime.setText("未知时间");
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
                textItems.setText("无商品信息");
            }
            
            // 点击事件
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(order);
                }
            });
        }
    }
}
