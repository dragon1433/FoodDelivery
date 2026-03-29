package com.fooddelivery.app.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.fooddelivery.app.data.model.Address;
import com.fooddelivery.app.data.repository.AddressRepository;
import java.util.List;

/**
 * 地址 ViewModel
 */
public class AddressViewModel extends AndroidViewModel {
    
    private final AddressRepository repository;
    private final LiveData<List<Address>> addresses;
    
    public AddressViewModel(@NonNull Application application) {
        super(application);
        repository = new AddressRepository(application);
        addresses = repository.getAllAddresses();
    }
    
    public LiveData<List<Address>> getAddresses() {
        return addresses;
    }
    
    public void addAddress(Address address) {
        repository.insertAddress(address);
    }
    
    public void updateAddress(Address address) {
        repository.updateAddress(address);
    }
    
    public void deleteAddress(Address address) {
        repository.deleteAddress(address);
    }
}
