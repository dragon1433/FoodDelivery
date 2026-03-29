package com.fooddelivery.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Address;
import java.util.ArrayList;
import java.util.List;

/**
 * 地址列表适配器
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {
    
    private List<Address> addresses = new ArrayList<>();
    private OnItemClickListener listener;
    
    public interface OnItemClickListener {
        void onItemClick(Address address);
        void onEditClick(Address address);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_address, parent, false);
        return new AddressViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Address address = addresses.get(position);
        holder.bind(address);
    }
    
    @Override
    public int getItemCount() {
        return addresses.size();
    }
    
    class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPhone;
        TextView textAddress;
        ImageView btnEdit;
        
        AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textPhone = itemView.findViewById(R.id.text_phone);
            textAddress = itemView.findViewById(R.id.text_address);
            btnEdit = itemView.findViewById(R.id.btn_edit);
        }
        
        void bind(Address address) {
            textName.setText(address.getName());
            textPhone.setText(" " + formatPhone(address.getPhone()));
            textAddress.setText(address.getFullAddress());
            
            // 编辑按钮点击
            btnEdit.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEditClick(address);
                }
            });
            
            // 整体点击
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(address);
                }
            });
        }
        
        private String formatPhone(String phone) {
            if (phone.length() == 11) {
                return phone.substring(0, 3) + "****" + phone.substring(7);
            }
            return phone;
        }
    }
}
