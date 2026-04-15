package com.fooddelivery.app.ui.screens.login;

import android.content.SharedPreferences;
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
import com.fooddelivery.app.databinding.FragmentLoginBinding;
import com.fooddelivery.app.ui.viewmodels.UserViewModel;

/**
 * Login Fragment - Java + XML Version
 */
public class LoginFragment extends Fragment {
    
    private FragmentLoginBinding binding;
    private UserViewModel userViewModel;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_REMEMBER_ME = "remember_me";
    private static final String KEY_PHONE = "phone";
    private boolean hasNavigated = false; // Prevent multiple navigations

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialize ViewModel
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, 
                requireActivity().MODE_PRIVATE);
        
        setupViews();
        loadSavedCredentials();
    }

    private void setupViews() {
        // Login button click
        binding.loginButton.setOnClickListener(v -> performLogin());
        
        // Forgot password - hidden
        binding.forgotPasswordText.setVisibility(View.GONE);
        
        // Register link click
        binding.registerText.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_registerFragment);
        });
    }

    private void loadSavedCredentials() {
        boolean rememberMe = sharedPreferences.getBoolean(KEY_REMEMBER_ME, false);
        if (rememberMe) {
            String savedPhone = sharedPreferences.getString(KEY_PHONE, "");
            if (!savedPhone.isEmpty()) {
                binding.phoneEditText.setText(savedPhone);
                binding.rememberMeCheckbox.setChecked(true);
            }
        }
    }

    private void performLogin() {
        String phone = binding.phoneEditText.getText() != null ? 
                binding.phoneEditText.getText().toString().trim() : "";
        String password = binding.passwordEditText.getText() != null ? 
                binding.passwordEditText.getText().toString().trim() : "";
        
        // Validate input
        if (phone.isEmpty()) {
            binding.phoneInputLayout.setError(getString(R.string.login_error_phone_empty));
            return;
        } else {
            binding.phoneInputLayout.setError(null);
        }
        
        if (password.isEmpty()) {
            binding.passwordInputLayout.setError(getString(R.string.login_error_password_empty));
            return;
        } else {
            binding.passwordInputLayout.setError(null);
        }
        
        // Show loading
        binding.loginButton.setEnabled(false);
        binding.loginButton.setText("Logging in...");
        
        // Perform real login
        userViewModel.login(phone, password, new UserViewModel.LoginCallback() {
            @Override
            public void onSuccess(com.fooddelivery.app.data.model.User user) {
                requireActivity().runOnUiThread(() -> {
                    // Save "Remember Me" preference
                    boolean rememberMe = binding.rememberMeCheckbox.isChecked();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(KEY_REMEMBER_ME, rememberMe);
                    if (rememberMe) {
                        editor.putString(KEY_PHONE, phone);
                    } else {
                        editor.remove(KEY_PHONE);
                    }
                    editor.apply();
                    
                    Toast.makeText(getContext(), R.string.login_success, Toast.LENGTH_SHORT).show();
                    
                    // Navigate to home
                    Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_mainFragment);
                });
            }
            
            @Override
            public void onError(String message) {
                requireActivity().runOnUiThread(() -> {
                    android.util.Log.e("LoginFragment", "Login error: " + message);
                    binding.loginButton.setEnabled(true);
                    binding.loginButton.setText(getString(R.string.login_button));
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
