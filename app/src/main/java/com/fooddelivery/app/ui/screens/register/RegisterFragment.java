package com.fooddelivery.app.ui.screens.register;

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
import com.fooddelivery.app.databinding.FragmentRegisterBinding;

/**
 * 注册 Fragment - 简化版
 */
public class RegisterFragment extends Fragment {
    
    private FragmentRegisterBinding binding;

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
        setupViews();
    }

    private void setupViews() {
        // 注册按钮
        binding.registerButton.setOnClickListener(v -> performRegister());
        
        // 返回登录
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
        
        // 验证手机号
        if (phone.isEmpty()) {
            binding.registerPhoneInputLayout.setError(getString(R.string.login_error_phone_empty));
            return;
        } else {
            binding.registerPhoneInputLayout.setError(null);
        }
        
        // 验证密码
        if (password.isEmpty()) {
            binding.registerPasswordInputLayout.setError("请设置密码");
            return;
        } else {
            binding.registerPasswordInputLayout.setError(null);
        }
        
        if (password.length() < 6) {
            binding.registerPasswordInputLayout.setError(getString(R.string.register_error_password_short));
            return;
        }
        
        // 验证确认密码
        if (confirmPassword.isEmpty()) {
            binding.registerConfirmPasswordInputLayout.setError("请确认密码");
            return;
        } else {
            binding.registerConfirmPasswordInputLayout.setError(null);
        }
        
        if (!password.equals(confirmPassword)) {
            binding.registerConfirmPasswordInputLayout.setError(getString(R.string.register_error_password_mismatch));
            return;
        }
        
        // TODO: 调用注册API
        Toast.makeText(getContext(), R.string.register_success, Toast.LENGTH_SHORT).show();
        
        // 返回登录页面
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
