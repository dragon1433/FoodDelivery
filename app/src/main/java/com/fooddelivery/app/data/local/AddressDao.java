package com.fooddelivery.app.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.fooddelivery.app.data.model.Address;

import java.util.List;

@Dao
public interface AddressDao {
    @Query("SELECT * FROM addresses ORDER BY isDefault DESC, id")
    LiveData<List<Address>> getAllAddresses();
    
    @Query("SELECT * FROM addresses WHERE id = :addressId")
    Address getAddressById(long addressId);
    
    @Query("SELECT * FROM addresses WHERE isDefault = 1 LIMIT 1")
    Address getDefaultAddress();
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Address address);
    
    @Update
    void update(Address address);
    
    @Delete
    void delete(Address address);
    
    @Query("UPDATE addresses SET isDefault = 0")
    void clearDefaultAddress();
}
