package com.fooddelivery.app.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.fooddelivery.app.data.model.Address;
import com.fooddelivery.app.data.repository.AddressRepository;
import java.util.List;

/**
 * 地址 ViewModel
 */
public class AddressViewModel extends AndroidViewModel {
    
    private final AddressRepository repository;
    private final LiveData<List<Address>> addresses;
    private final MutableLiveData<Address> selectedAddress = new MutableLiveData<>();
    
    public AddressViewModel(@NonNull Application application) {
        super(application);
        repository = new AddressRepository(application);
        addresses = repository.getAllAddresses();
    }
    
    public LiveData<List<Address>> getAddresses() {
        return addresses;
    }
    
    public LiveData<Address> getSelectedAddress() {
        return selectedAddress;
    }
    
    public void setSelectedAddress(Address address) {
        selectedAddress.setValue(address);
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
    
    public void setDefaultAddress(Address address) {
        repository.setDefaultAddress(address);
    }
}
