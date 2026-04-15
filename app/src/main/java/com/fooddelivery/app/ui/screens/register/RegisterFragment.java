package com.fooddelivery.app.ui.screens.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.fooddelivery.app.R;
import com.fooddelivery.app.databinding.FragmentRegisterBinding;
import com.fooddelivery.app.ui.viewmodels.UserViewModel;

/**
 * Register Fragment - Real Registration
 */
public class RegisterFragment extends Fragment {
    
    private FragmentRegisterBinding binding;
    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialize ViewModel
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        
        setupViews();
    }

    private void setupViews() {
        // Register button
        binding.registerButton.setOnClickListener(v -> performRegister());
        
        // Back to login
        binding.backToLoginText.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).popBackStack();
        });
    }

    private void performRegister() {
        String phone = binding.registerPhoneEditText.getText() != null ? 
                binding.registerPhoneEditText.getText().toString().trim() : "";
        String password = binding.registerPasswordEditText.getText() != null ? 
                binding.registerPasswordEditText.getText().toString().trim() : "";
        String confirmPassword = binding.registerConfirmPasswordEditText.getText() != null ? 
                binding.registerConfirmPasswordEditText.getText().toString().trim() : "";
        
        // Validate phone
        if (phone.isEmpty()) {
            binding.registerPhoneInputLayout.setError(getString(R.string.login_error_phone_empty));
            return;
        } else {
            binding.registerPhoneInputLayout.setError(null);
        }
        
        // Validate password
        if (password.isEmpty()) {
            binding.registerPasswordInputLayout.setError("Please set password");
            return;
        } else {
            binding.registerPasswordInputLayout.setError(null);
        }
        
        if (password.length() < 6) {
            binding.registerPasswordInputLayout.setError(getString(R.string.register_error_password_short));
            return;
        }
        
        // Validate confirm password
        if (confirmPassword.isEmpty()) {
            binding.registerConfirmPasswordInputLayout.setError("Please confirm password");
            return;
        } else {
            binding.registerConfirmPasswordInputLayout.setError(null);
        }
        
        if (!password.equals(confirmPassword)) {
            binding.registerConfirmPasswordInputLayout.setError(getString(R.string.register_error_password_mismatch));
            return;
        }
        
        // Show loading
        binding.registerButton.setEnabled(false);
        binding.registerButton.setText("Registering...");
        
        // Perform real registration (use phone last 4 digits as name)
        String name;
        if (phone.length() >= 4) {
            name = "User_" + phone.substring(phone.length() - 4);
        } else {
            name = "User_" + phone;
        }
        
        userViewModel.register(phone, password, name, new UserViewModel.RegisterCallback() {
            @Override
            public void onSuccess(com.fooddelivery.app.data.model.User user) {
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), R.string.register_success, Toast.LENGTH_SHORT).show();
                    
                    // Navigate back to login
                    Navigation.findNavController(requireView()).popBackStack();
                });
            }
            
            @Override
            public void onError(String message) {
                requireActivity().runOnUiThread(() -> {
                    binding.registerButton.setEnabled(true);
                    binding.registerButton.setText(getString(R.string.register_button));
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
