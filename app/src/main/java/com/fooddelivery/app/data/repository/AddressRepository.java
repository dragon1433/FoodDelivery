package com.fooddelivery.app.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.local.AddressDao;
import com.fooddelivery.app.data.model.Address;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 地址数据仓库
 */
public class AddressRepository {
    
    private final AddressDao addressDao;
    private final ExecutorService executor;
    private final LiveData<java.util.List<Address>> addresses;
    
    public AddressRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        this.addressDao = database.addressDao();
        this.executor = Executors.newSingleThreadExecutor();
        // Use Room's LiveData - it will automatically update when data changes
        this.addresses = addressDao.getAllAddresses();
    }
    
    public LiveData<java.util.List<Address>> getAllAddresses() {
        return addresses;
    }
    
    public LiveData<Address> getAddressById(long addressId) {
        MutableLiveData<Address> address = new MutableLiveData<>();
        executor.execute(() -> {
            Address a = addressDao.getAddressById(addressId);
            address.postValue(a);
        });
        return address;
    }
    
    public void insertAddress(Address address) {
        executor.execute(() -> {
            addressDao.insert(address);
            // Room LiveData will automatically notify observers
        });
    }
    
    public void updateAddress(Address address) {
        executor.execute(() -> {
            addressDao.update(address);
            // Room LiveData will automatically notify observers
        });
    }
    
    public void deleteAddress(Address address) {
        executor.execute(() -> {
            addressDao.delete(address);
            // Room LiveData will automatically notify observers
        });
    }
    
    public void setDefaultAddress(Address address) {
        executor.execute(() -> {
            // Clear all default addresses
            addressDao.clearDefaultAddress();
            // Set the selected address as default
            address.setDefault(true);
            addressDao.update(address);
            // Room LiveData will automatically notify observers
        });
    }
}
