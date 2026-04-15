package com.fooddelivery.app.ui.screens.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.bumptech.glide.Glide;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.User;
import com.fooddelivery.app.ui.screens.address.AddressFragment;
import com.fooddelivery.app.ui.viewmodels.UserViewModel;

/**
 * Profile Fragment - Display current user information
 */
public class ProfileFragment extends Fragment {
    
    private ImageView imageAvatar;
    private TextView textUsername;
    private TextView textPhone;
    private LinearLayout layoutAddress;
    private LinearLayout layoutSettings;
    private LinearLayout layoutMyOrders;
    private LinearLayout layoutShoppingCart;
    private LinearLayout layoutCoupons;
    private LinearLayout layoutVip;
    private LinearLayout layoutCustomerService;
    private LinearLayout layoutLogout;
    
    private UserViewModel userViewModel;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialize ViewModel
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        
        // Initialize views
        imageAvatar = view.findViewById(R.id.image_avatar);
        textUsername = view.findViewById(R.id.text_username);
        textPhone = view.findViewById(R.id.text_phone);
        layoutAddress = view.findViewById(R.id.layout_address);
        layoutSettings = view.findViewById(R.id.layout_settings);
        layoutMyOrders = view.findViewById(R.id.layout_my_orders);
        layoutShoppingCart = view.findViewById(R.id.layout_shopping_cart);
        layoutCoupons = view.findViewById(R.id.layout_coupons);
        layoutVip = view.findViewById(R.id.layout_vip);
        layoutCustomerService = view.findViewById(R.id.layout_customer_service);
        layoutLogout = view.findViewById(R.id.layout_logout);
        
        // Observe current user and display real user info
        userViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                // Display registered user information
                textUsername.setText(user.getName());
                // Mask phone number for privacy: 138****1234
                String maskedPhone = maskPhoneNumber(user.getPhone());
                textPhone.setText(maskedPhone);
                
                // Load real avatar image from Unsplash
                Glide.with(this)
                    .load("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=200")
                    .circleCrop()
                    .placeholder(R.drawable.ic_profile)
                    .into(imageAvatar);
                
                // Show logout button when user is logged in
                layoutLogout.setVisibility(View.VISIBLE);
            } else {
                // If no user logged in, show default
                textUsername.setText("Guest");
                textPhone.setText("Not logged in");
                
                // Load default avatar
                Glide.with(this)
                    .load("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=200")
                    .circleCrop()
                    .placeholder(R.drawable.ic_profile)
                    .into(imageAvatar);
                
                // Hide logout button when not logged in
                layoutLogout.setVisibility(View.GONE);
            }
        });
        
        // My Orders click - switch to orders tab
        layoutMyOrders.setOnClickListener(v -> {
            if (getActivity() != null) {
                // Switch to orders page via bottom navigation
                com.google.android.material.bottomnavigation.BottomNavigationView bottomNav = 
                    getActivity().findViewById(R.id.bottom_navigation);
                bottomNav.setSelectedItemId(R.id.orderFragment);
            }
        });
        
        // Address management click
        layoutAddress.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_profile_fragment_to_address_fragment);
        });
        
        // Shopping cart click - switch to cart tab
        layoutShoppingCart.setOnClickListener(v -> {
            if (getActivity() != null) {
                // Switch to cart page via bottom navigation
                com.google.android.material.bottomnavigation.BottomNavigationView bottomNav = 
                    getActivity().findViewById(R.id.bottom_navigation);
                bottomNav.setSelectedItemId(R.id.cartFragment);
            }
        });
        
        // Settings click
        layoutSettings.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Settings coming soon...", Toast.LENGTH_SHORT).show();
        });
        
        // Coupons/Red packets click
        layoutCoupons.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Coupons coming soon...", Toast.LENGTH_SHORT).show();
        });
        
        // VIP membership click
        layoutVip.setOnClickListener(v -> {
            Toast.makeText(getContext(), "VIP membership coming soon...", Toast.LENGTH_SHORT).show();
        });
        
        // Customer service & help click
        layoutCustomerService.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Customer service coming soon...", Toast.LENGTH_SHORT).show();
        });
        
        // Logout click
        layoutLogout.setOnClickListener(v -> {
            showLogoutConfirmationDialog();
        });
    }
    
    // Mask phone number for privacy: 13812345678 -> 138****5678
    private String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() < 7) {
            return phone;
        }
        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
    }
    
    // Show logout confirmation dialog
    private void showLogoutConfirmationDialog() {
        new android.app.AlertDialog.Builder(getContext())
            .setTitle(R.string.profile_logout_confirm_title)
            .setMessage(R.string.profile_logout_confirm_message)
            .setPositiveButton(R.string.profile_logout, (dialog, which) -> {
                performLogout();
            })
            .setNegativeButton(R.string.cancel, (dialog, which) -> {
                dialog.dismiss();
            })
            .show();
    }
    
    // Perform logout
    private void performLogout() {
        User currentUser = userViewModel.getCurrentUser().getValue();
        if (currentUser != null) {
            userViewModel.logout(currentUser.getId(), new UserViewModel.LogoutCallback() {
                @Override
                public void onSuccess() {
                    requireActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), R.string.profile_logout_success, Toast.LENGTH_SHORT).show();
                        
                        // Clear user session
                        clearUserSession();
                        
                        // Navigate to login page using NavUtils
                        androidx.navigation.NavController navController = Navigation.findNavController(requireView());
                        
                        // Pop all fragments from back stack
                        navController.popBackStack(R.id.loginFragment, false);
                        
                        // If still not at login, navigate to it
                        if (navController.getCurrentDestination() != null && 
                            navController.getCurrentDestination().getId() != R.id.loginFragment) {
                            navController.navigate(R.id.loginFragment);
                        }
                    });
                }
                
                @Override
                public void onError(String message) {
                    requireActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), getString(R.string.profile_logout_failed) + ": " + message, Toast.LENGTH_SHORT).show();
                    });
                }
            });
        }
    }
    
    // Clear user session only
    private void clearUserSession() {
        if (getActivity() != null) {
            android.content.SharedPreferences sessionPrefs = getActivity().getSharedPreferences(
                "user_session", getActivity().MODE_PRIVATE);
            sessionPrefs.edit().clear().apply();
        }
    }
}
