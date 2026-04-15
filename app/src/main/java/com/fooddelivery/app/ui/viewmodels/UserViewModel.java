package com.fooddelivery.app.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.fooddelivery.app.data.model.User;
import com.fooddelivery.app.data.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    
    private UserRepository userRepository;
    
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }
    
    // Register
    public interface RegisterCallback {
        void onSuccess(User user);
        void onError(String message);
    }
    
    public void register(String phone, String password, String name, RegisterCallback callback) {
        userRepository.register(phone, password, name, new UserRepository.RegisterCallback() {
            @Override
            public void onSuccess(User user) {
                callback.onSuccess(user);
            }
            
            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
    
    // Login
    public interface LoginCallback {
        void onSuccess(User user);
        void onError(String message);
    }
    
    public void login(String phone, String password, LoginCallback callback) {
        userRepository.login(phone, password, new UserRepository.LoginCallback() {
            @Override
            public void onSuccess(User user) {
                callback.onSuccess(user);
            }
            
            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
    
    // Get current user
    public LiveData<User> getCurrentUser() {
        return userRepository.getCurrentUser();
    }
    
    // Update user
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
    
    // Logout
    public interface LogoutCallback {
        void onSuccess();
        void onError(String message);
    }
    
    public void logout(long userId, LogoutCallback callback) {
        userRepository.logout(userId, new UserRepository.LogoutCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
            
            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
}
