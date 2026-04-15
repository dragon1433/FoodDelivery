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
import com.fooddelivery.app.data.model.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Banner Adapter for ViewPager2
 */
public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    
    private List<Banner> banners = new ArrayList<>();
    private OnBannerClickListener listener;
    
    public interface OnBannerClickListener {
        void onBannerClick(Banner banner);
    }
    
    public void setOnBannerClickListener(OnBannerClickListener listener) {
        this.listener = listener;
    }
    
    public void setBanners(List<Banner> banners) {
        this.banners = banners != null ? banners : new ArrayList<>();
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_banner, parent, false);
        return new BannerViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner banner = banners.get(position);
        holder.bind(banner);
    }
    
    @Override
    public int getItemCount() {
        return banners.size();
    }
    
    class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageBanner;
        private TextView textTitle;
        
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBanner = itemView.findViewById(R.id.image_banner);
            textTitle = itemView.findViewById(R.id.text_banner_title);
        }
        
        public void bind(Banner banner) {
            // Load banner image using Glide
            Glide.with(itemView.getContext())
                .load(banner.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_home)
                .error(R.drawable.ic_home)
                .into(imageBanner);
            
            // Set title
            if (banner.getTitle() != null && !banner.getTitle().isEmpty()) {
                textTitle.setText(banner.getTitle());
                textTitle.setVisibility(View.VISIBLE);
            } else {
                textTitle.setVisibility(View.GONE);
            }
            
            // Set click listener
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBannerClick(banner);
                }
            });
        }
    }
}
