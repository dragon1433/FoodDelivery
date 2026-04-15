package com.fooddelivery.app.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.local.UserDao;
import com.fooddelivery.app.data.model.User;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    
    private UserDao userDao;
    private ExecutorService executorService;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "user_session";
    private static final String KEY_CURRENT_USER_ID = "current_user_id";
    private androidx.lifecycle.MutableLiveData<User> currentUserLiveData = new androidx.lifecycle.MutableLiveData<>();
    
    public UserRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        userDao = database.userDao();
        executorService = Executors.newSingleThreadExecutor();
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    // Get current user ID from SharedPreferences
    private long getCurrentUserId() {
        return sharedPreferences.getLong(KEY_CURRENT_USER_ID, -1);
    }
    
    // Save current user ID to SharedPreferences
    private void saveCurrentUserId(long userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_CURRENT_USER_ID, userId);
        editor.apply();
    }
    
    // Clear current user ID from SharedPreferences
    private void clearCurrentUserId() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_CURRENT_USER_ID);
        editor.apply();
    }
    
    // Register new user
    public interface RegisterCallback {
        void onSuccess(User user);
        void onError(String message);
    }
    
    public void register(String phone, String password, String name, RegisterCallback callback) {
        executorService.execute(() -> {
            try {
                // Check if phone already exists
                User existingUser = userDao.getUserByPhone(phone);
                if (existingUser != null) {
                    callback.onError("Phone number already registered");
                    return;
                }
                
                // Create new user
                User newUser = new User(phone, password, name);
                long userId = userDao.insert(newUser);
                
                if (userId > 0) {
                    newUser.setId(userId);
                    // Auto login after registration
                    saveCurrentUserId(userId);
                    currentUserLiveData.postValue(newUser);
                    callback.onSuccess(newUser);
                } else {
                    callback.onError("Registration failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                callback.onError("Registration error: " + e.getMessage());
            }
        });
    }
    
    // Login
    public interface LoginCallback {
        void onSuccess(User user);
        void onError(String message);
    }
    
    public void login(String phone, String password, LoginCallback callback) {
        executorService.execute(() -> {
            try {
                android.util.Log.d("UserRepository", "Attempting login with phone: " + phone);
                User user = userDao.login(phone, password);
                if (user != null) {
                    android.util.Log.d("UserRepository", "Login successful for user ID: " + user.getId());
                    // Save current user ID to SharedPreferences
                    saveCurrentUserId(user.getId());
                    currentUserLiveData.postValue(user);
                    callback.onSuccess(user);
                } else {
                    android.util.Log.e("UserRepository", "Login failed: Invalid credentials");
                    callback.onError("Invalid phone number or password");
                }
            } catch (Exception e) {
                android.util.Log.e("UserRepository", "Login error: " + e.getMessage(), e);
                e.printStackTrace();
                callback.onError("Login error: " + e.getMessage());
            }
        });
    }
    
    // Get current user
    public LiveData<User> getCurrentUser() {
        return currentUserLiveData;
    }
    
    // Update user info
    public void updateUser(User user) {
        executorService.execute(() -> {
            userDao.update(user);
        });
    }
    
    // Logout - clear current user session (don't delete user data)
    public interface LogoutCallback {
        void onSuccess();
        void onError(String message);
    }
    
    public void logout(long userId, LogoutCallback callback) {
        executorService.execute(() -> {
            try {
                // Clear current user ID from SharedPreferences (don't delete user data)
                clearCurrentUserId();
                // Update LiveData to notify observers
                currentUserLiveData.postValue(null);
                callback.onSuccess();
            } catch (Exception e) {
                e.printStackTrace();
                callback.onError("Logout error: " + e.getMessage());
            }
        });
    }
}
