package com.fooddelivery.app.ui.screens.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Address;
import com.fooddelivery.app.ui.adapters.AddressAdapter;
import com.fooddelivery.app.ui.viewmodels.AddressViewModel;
import java.util.List;

/**
 * Address Management Fragment
 */
public class AddressFragment extends Fragment implements AddressAdapter.OnItemClickListener {
    
    private ImageView btnBack;
    private ImageView btnAdd;
    private RecyclerView recyclerAddresses;
    
    private AddressViewModel viewModel;
    private AddressAdapter adapter;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_address, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialize views
        btnBack = view.findViewById(R.id.btn_back);
        btnAdd = view.findViewById(R.id.btn_add);
        recyclerAddresses = view.findViewById(R.id.recycler_addresses);
        
        // Setup back button
        btnBack.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        
        // Add address button
        btnAdd.setOnClickListener(v -> {
            showAddAddressDialog();
        });
        
        // Setup RecyclerView
        adapter = new AddressAdapter();
        adapter.setOnItemClickListener(this);
        recyclerAddresses.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAddresses.setAdapter(adapter);
        
        // Initialize ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);
        
        // Observe address data
        viewModel.getAddresses().observe(getViewLifecycleOwner(), addresses -> {
            if (addresses != null) {
                adapter.setAddresses(addresses);
            }
        });
    }
    
    @Override
    public void onItemClick(Address address) {
        // Set this address as default
        viewModel.setDefaultAddress(address);
        
        // Show confirmation
        Toast.makeText(getContext(), "Selected: " + address.getDetailAddress(), Toast.LENGTH_SHORT).show();
        
        // Navigate back to previous page (checkout)
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
    
    @Override
    public void onEditClick(Address address) {
        // Edit address
        showEditAddressDialog(address);
    }
    
    // Show add address dialog
    private void showAddAddressDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Add Delivery Address");
        
        // Create layout
        android.widget.LinearLayout layout = new android.widget.LinearLayout(getContext());
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);
        
        // Name input
        final android.widget.EditText nameInput = new android.widget.EditText(getContext());
        nameInput.setHint("Receiver name");
        layout.addView(nameInput);
        
        // Phone input
        final android.widget.EditText phoneInput = new android.widget.EditText(getContext());
        phoneInput.setHint("Phone number");
        layout.addView(phoneInput);
        
        // Address input
        final android.widget.EditText addressInput = new android.widget.EditText(getContext());
        addressInput.setHint("Detailed address");
        layout.addView(addressInput);
        
        builder.setView(layout);
        
        // Cancel button
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        
        // Confirm button
        builder.setPositiveButton("Save", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String address = addressInput.getText().toString().trim();
            
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Create new address
            Address newAddress = new Address();
            newAddress.setId(System.currentTimeMillis());
            newAddress.setName(name);
            newAddress.setPhone(phone);
            newAddress.setDetailAddress(address);
            newAddress.setLatitude(22.5428);
            newAddress.setLongitude(114.0543);
            
            // Save to database
            viewModel.addAddress(newAddress);
            
            Toast.makeText(getContext(), "Address added successfully", Toast.LENGTH_SHORT).show();
        });
        
        builder.show();
    }
    
    // Show edit address dialog
    private void showEditAddressDialog(Address address) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Edit Delivery Address");
        
        android.widget.LinearLayout layout = new android.widget.LinearLayout(getContext());
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);
        
        final android.widget.EditText nameInput = new android.widget.EditText(getContext());
        nameInput.setText(address.getName());
        nameInput.setHint("Receiver name");
        layout.addView(nameInput);
        
        final android.widget.EditText phoneInput = new android.widget.EditText(getContext());
        phoneInput.setText(address.getPhone());
        phoneInput.setHint("Phone number");
        layout.addView(phoneInput);
        
        final android.widget.EditText addressInput = new android.widget.EditText(getContext());
        addressInput.setText(address.getDetailAddress());
        addressInput.setHint("Detailed address");
        layout.addView(addressInput);
        
        builder.setView(layout);
        
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        
        builder.setPositiveButton("Save", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String addr = addressInput.getText().toString().trim();
            
            if (name.isEmpty() || phone.isEmpty() || addr.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            
            address.setName(name);
            address.setPhone(phone);
            address.setDetailAddress(addr);
            
            viewModel.updateAddress(address);
            
            Toast.makeText(getContext(), "Address updated", Toast.LENGTH_SHORT).show();
        });
        
        builder.show();
    }
}
