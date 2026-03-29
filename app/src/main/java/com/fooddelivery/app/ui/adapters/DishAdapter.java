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
import com.fooddelivery.app.data.model.Dish;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜品列表适配器
 */
public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    
    private List<Dish> dishes = new ArrayList<>();
    private Map<Long, Integer> cartQuantities = new HashMap<>();
    private OnItemClickListener listener;
    
    public interface OnItemClickListener {
        void onAddToCart(Dish dish, int quantity);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        notifyDataSetChanged();
    }
    
    public void updateCartQuantities(Map<Long, Integer> quantities) {
        this.cartQuantities = quantities;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dish, parent, false);
        return new DishViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.bind(dish);
    }
    
    @Override
    public int getItemCount() {
        return dishes.size();
    }
    
    class DishViewHolder extends RecyclerView.ViewHolder {
        ImageView imageDish;
        TextView textDishName;
        TextView textDishDescription;
        TextView textDishPrice;
        ImageView btnDecrease;
        TextView textQuantity;
        ImageView btnIncrease;
        
        DishViewHolder(@NonNull View itemView) {
            super(itemView);
            imageDish = itemView.findViewById(R.id.image_dish);
            textDishName = itemView.findViewById(R.id.text_dish_name);
            textDishDescription = itemView.findViewById(R.id.text_dish_description);
            textDishPrice = itemView.findViewById(R.id.text_dish_price);
            btnDecrease = itemView.findViewById(R.id.btn_decrease);
            textQuantity = itemView.findViewById(R.id.text_quantity);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
        }
        
        void bind(Dish dish) {
            textDishName.setText(dish.getName());
            textDishDescription.setText(dish.getDescription());
            textDishPrice.setText(String.format("¥%.1f", dish.getPrice()));
            
            // 加载图片 - 将字符串转换为资源 ID
            String imageUrl = dish.getImageUrl();
            int resourceId = getImageResource(imageUrl);
            
            if (resourceId != 0) {
                Glide.with(itemView.getContext())
                        .load(resourceId)
                        .placeholder(R.drawable.ic_home)
                        .into(imageDish);
            } else {
                imageDish.setImageResource(R.drawable.ic_home);
            }
            
            // 更新数量显示
            int quantity = cartQuantities.getOrDefault(dish.getId(), 0);
            textQuantity.setText(String.valueOf(quantity));
            
            // 加减按钮点击事件
            btnIncrease.setOnClickListener(v -> {
                int currentQty = cartQuantities.getOrDefault(dish.getId(), 0);
                int newQty = currentQty + 1;
                cartQuantities.put(dish.getId(), newQty);
                textQuantity.setText(String.valueOf(newQty));
                
                // 添加到购物车 - 增加 1 个
                if (listener != null) {
                    CartItem cartItem = new CartItem();
                    cartItem.setFromDish(dish, 1); // 增加 1 个
                    listener.onAddToCart(dish, 1);
                }
            });
            
            btnDecrease.setOnClickListener(v -> {
                int currentQty = cartQuantities.getOrDefault(dish.getId(), 0);
                if (currentQty > 0) {
                    int newQty = currentQty - 1;
                    cartQuantities.put(dish.getId(), newQty);
                    textQuantity.setText(String.valueOf(newQty));
                    
                    // 从购物车减少 1 个
                    if (listener != null) {
                        CartItem cartItem = new CartItem();
                        cartItem.setFromDish(dish, -1); // 减少 1 个 (负数)
                        listener.onAddToCart(dish, -1);
                    }
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
