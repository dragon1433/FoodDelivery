package com.fooddelivery.app.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.CartItem;
import com.fooddelivery.app.data.model.Combo;
import com.fooddelivery.app.data.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Combo Adapter for displaying meal combos
 */
public class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.ComboViewHolder> {
    
    private List<Combo> combos = new ArrayList<>();
    private Context context;
    private CartRepository cartRepository;
    
    public ComboAdapter(Context context) {
        this.context = context;
        this.cartRepository = new CartRepository(context);
    }
    
    public void setCombos(List<Combo> combos) {
        this.combos = combos != null ? combos : new ArrayList<>();
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public ComboViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_combo, parent, false);
        return new ComboViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ComboViewHolder holder, int position) {
        Combo combo = combos.get(position);
        holder.bind(combo);
    }
    
    @Override
    public int getItemCount() {
        return combos.size();
    }
    
    class ComboViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageCombo;
        private TextView textComboName;
        private TextView textComboDescription;
        private TextView textComboPrice;
        private Button btnAddCombo;
        
        public ComboViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCombo = itemView.findViewById(R.id.image_combo);
            textComboName = itemView.findViewById(R.id.text_combo_name);
            textComboDescription = itemView.findViewById(R.id.text_combo_description);
            textComboPrice = itemView.findViewById(R.id.text_combo_price);
            btnAddCombo = itemView.findViewById(R.id.btn_add_combo);
        }
        
        public void bind(Combo combo) {
            // Load combo image
            Glide.with(itemView.getContext())
                .load(combo.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_home)
                .error(R.drawable.ic_home)
                .into(imageCombo);
            
            // Set text
            textComboName.setText(combo.getName());
            textComboDescription.setText(combo.getDescription());
            textComboPrice.setText(String.format("¥%.2f", combo.getPrice()));
            
            // Add button click
            btnAddCombo.setOnClickListener(v -> {
                addToCart(combo);
            });
        }
    }
    
    /**
     * Add combo to cart as a special cart item
     */
    private void addToCart(Combo combo) {
        // Create a CartItem from the combo
        // Use negative dishId to indicate it's a combo (to avoid conflict with regular dishes)
        CartItem cartItem = new CartItem();
        cartItem.setId(System.currentTimeMillis());
        cartItem.setDishId(-combo.getId()); // Negative ID to distinguish from regular dishes
        cartItem.setRestaurantId(0); // Combo doesn't belong to a specific restaurant
        cartItem.setName("🍱 " + combo.getName()); // Add emoji to indicate it's a combo
        cartItem.setPrice(combo.getPrice());
        cartItem.setPackingFee(0); // No packing fee for combos
        cartItem.setImageUrl(combo.getImageUrl());
        cartItem.setQuantity(1);
        cartItem.setCategoryName("Combo Meal");
        
        // Add to cart
        cartRepository.addToCart(cartItem);
        
        // Show success message
        Toast.makeText(context, 
            "Added " + combo.getName() + " to cart!", 
            Toast.LENGTH_SHORT).show();
    }
}
