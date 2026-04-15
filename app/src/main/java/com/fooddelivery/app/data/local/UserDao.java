package com.fooddelivery.app.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.fooddelivery.app.data.model.User;

@Dao
public interface UserDao {
    
    @Insert
    long insert(User user);
    
    @Update
    void update(User user);
    
    @Query("SELECT * FROM users WHERE phone = :phone LIMIT 1")
    User getUserByPhone(String phone);
    
    @Query("SELECT * FROM users WHERE phone = :phone AND password = :password LIMIT 1")
    User login(String phone, String password);
    
    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    LiveData<User> getCurrentUser(long userId);
    
    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    User getUserById(long userId);
    
    @Query("DELETE FROM users WHERE id = :userId")
    void logout(long userId);
}
