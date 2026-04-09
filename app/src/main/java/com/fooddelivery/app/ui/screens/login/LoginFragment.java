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
import androidx.navigation.Navigation;
import com.fooddelivery.app.R;
import com.fooddelivery.app.databinding.FragmentLoginBinding;
import com.google.android.material.textfield.TextInputEditText;

/**
 * 登录 Fragment - Java + XML 版本
 */
public class LoginFragment extends Fragment {
    
    private FragmentLoginBinding binding;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_REMEMBER_ME = "remember_me";
    private static final String KEY_PHONE = "phone";

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
        
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, 
                requireActivity().MODE_PRIVATE);
        
        setupViews();
        loadSavedCredentials();
    }

    private void setupViews() {
        // 登录按钮点击事件
        binding.loginButton.setOnClickListener(v -> performLogin());
        
        // 忘记密码 - 隐藏
        binding.forgotPasswordText.setVisibility(View.GONE);
        
        // 注册链接点击事件
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
        
        // 验证输入
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
        
        // 保存登录状态
        boolean rememberMe = binding.rememberMeCheckbox.isChecked();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_REMEMBER_ME, rememberMe);
        if (rememberMe) {
            editor.putString(KEY_PHONE, phone);
        } else {
            editor.remove(KEY_PHONE);
        }
        editor.apply();
        
        // TODO: 这里应该调用实际的登录API
        // 模拟登录成功
        Toast.makeText(getContext(), R.string.login_success, Toast.LENGTH_SHORT).show();
        
        // 导航到主页
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_mainFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
