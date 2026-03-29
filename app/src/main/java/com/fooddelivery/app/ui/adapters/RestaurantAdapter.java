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
import com.fooddelivery.app.data.model.Restaurant;
import java.util.ArrayList;
import java.util.List;

/**
 * 餐厅列表适配器
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    
    private List<Restaurant> restaurants = new ArrayList<>();
    private OnItemClickListener listener;
    
    public interface OnItemClickListener {
        void onItemClick(Restaurant restaurant);
        void onFavoriteClick(Restaurant restaurant, int position);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.bind(restaurant, position);
    }
    
    @Override
    public int getItemCount() {
        return restaurants.size();
    }
    
    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView imageRestaurant;
        TextView textName;
        TextView textEnglishName;
        TextView textRating;
        TextView textMonthlySales;
        TextView textDeliveryInfo;
        TextView textDistance;
        ImageView btnFavorite;
        
        RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            imageRestaurant = itemView.findViewById(R.id.image_restaurant);
            textName = itemView.findViewById(R.id.text_name);
            textEnglishName = itemView.findViewById(R.id.text_english_name);
            textRating = itemView.findViewById(R.id.text_rating);
            textMonthlySales = itemView.findViewById(R.id.text_monthly_sales);
            textDeliveryInfo = itemView.findViewById(R.id.text_delivery_info);
            textDistance = itemView.findViewById(R.id.text_distance);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);
        }
        
        void bind(Restaurant restaurant, int position) {
            textName.setText(restaurant.getName());
            textEnglishName.setText(restaurant.getEnglishName());
            textRating.setText(String.valueOf(restaurant.getRating()));
            textMonthlySales.setText("月售 " + restaurant.getMonthlySales());
            textDeliveryInfo.setText(restaurant.getDeliveryTime() + " · 配送费¥" + restaurant.getDeliveryFee());
            textDistance.setText(String.format("%.1fkm", restaurant.getDistance()));
            
            // 加载图片 - 将字符串转换为资源 ID
            String imageUrl = restaurant.getImageUrl();
            int resourceId = getImageResource(imageUrl);
            
            if (resourceId != 0) {
                Glide.with(itemView.getContext())
                        .load(resourceId)
                        .placeholder(R.drawable.ic_home)
                        .into(imageRestaurant);
            } else {
                imageRestaurant.setImageResource(R.drawable.ic_home);
            }
            
            // 设置收藏图标
            btnFavorite.setImageResource(restaurant.isFavorite() ? R.drawable.ic_profile : R.drawable.ic_home);
            
            // 点击事件
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(restaurant);
                }
            });
            
            btnFavorite.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onFavoriteClick(restaurant, position);
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
