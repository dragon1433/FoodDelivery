package com.fooddelivery.app.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.local.AddressDao;
import com.fooddelivery.app.data.model.Address;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 地址数据仓库
 */
public class AddressRepository {
    
    private final AddressDao addressDao;
    private final ExecutorService executor;
    private final MutableLiveData<List<Address>> addresses = new MutableLiveData<>();
    
    public AddressRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        this.addressDao = database.addressDao();
        this.executor = Executors.newSingleThreadExecutor();
    }
    
    public LiveData<List<Address>> getAllAddresses() {
        loadAddresses();
        return addresses;
    }
    
    private void loadAddresses() {
        executor.execute(() -> {
            List<Address> addressList = addressDao.getAllAddresses();
            addresses.postValue(addressList);
        });
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
            loadAddresses();
        });
    }
    
    public void updateAddress(Address address) {
        executor.execute(() -> {
            addressDao.update(address);
            loadAddresses();
        });
    }
    
    public void deleteAddress(Address address) {
        executor.execute(() -> {
            addressDao.delete(address);
            loadAddresses();
        });
    }
}
