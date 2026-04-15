package com.fooddelivery.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.CartItem;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车商品适配器
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    
    private List<CartItem> cartItems = new ArrayList<>();
    private OnItemClickListener listener;
    
    public interface OnItemClickListener {
        void onUpdateQuantity(CartItem item, int quantity);
        void onDeleteItem(CartItem item);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.bind(item);
    }
    
    @Override
    public int getItemCount() {
        return cartItems.size();
    }
    
    class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCartItem;
        TextView textCartItemName;
        TextView textCartItemPrice;
        ImageView btnCartDecrease;
        TextView textCartQuantity;
        ImageView btnCartIncrease;
        ImageView btnDelete;
        
        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCartItem = itemView.findViewById(R.id.image_cart_item);
            textCartItemName = itemView.findViewById(R.id.text_cart_item_name);
            textCartItemPrice = itemView.findViewById(R.id.text_cart_item_price);
            btnCartDecrease = itemView.findViewById(R.id.btn_cart_decrease);
            textCartQuantity = itemView.findViewById(R.id.text_cart_quantity);
            btnCartIncrease = itemView.findViewById(R.id.btn_cart_increase);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
        
        void bind(CartItem item) {
            textCartItemName.setText(item.getName());
            textCartItemPrice.setText(String.format("¥%.1f", item.getPrice()));
            textCartQuantity.setText(String.valueOf(item.getQuantity()));
            
            // Load image - support both URL and resource ID
            String imageUrl = item.getImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                if (imageUrl.startsWith("http")) {
                    // Load from URL
                    Glide.with(itemView.getContext())
                            .load(imageUrl)
                            .placeholder(R.drawable.ic_home)
                            .error(R.drawable.ic_home)
                            .into(imageCartItem);
                } else {
                    // Load from local resource
                    int resourceId = getImageResource(imageUrl);
                    if (resourceId != 0) {
                        Glide.with(itemView.getContext())
                                .load(resourceId)
                                .placeholder(R.drawable.ic_home)
                                .into(imageCartItem);
                    } else {
                        imageCartItem.setImageResource(R.drawable.ic_home);
                    }
                }
            } else {
                imageCartItem.setImageResource(R.drawable.ic_home);
            }
            
            // 增加数量
            btnCartIncrease.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onUpdateQuantity(item, item.getQuantity() + 1);
                }
            });
            
            // 减少数量
            btnCartDecrease.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onUpdateQuantity(item, item.getQuantity() - 1);
                }
            });
            
            // 删除商品
            btnDelete.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDeleteItem(item);
                }
            });
        }
        
        // 根据图片名称获取资源 ID
        private int getImageResource(String imageName) {
            if (imageName == null || imageName.isEmpty()) {
                return 0;
            }
            return itemView.getContext().getResources().getIdentifier(
                imageName, "drawable", itemView.getContext().getPackageName());
        }
    }
}
