package com.fooddelivery.app.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.data.repository.RestaurantRepository;
import java.util.List;

/**
 * 餐厅 ViewModel
 */
public class RestaurantViewModel extends AndroidViewModel {
    
    private final RestaurantRepository repository;
    private final LiveData<List<Restaurant>> allRestaurants;
    
    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        repository = new RestaurantRepository(application);
        allRestaurants = repository.getAllRestaurants();
    }
    
    public LiveData<List<Restaurant>> getAllRestaurants() {
        return allRestaurants;
    }
    
    public void toggleFavorite(long restaurantId) {
        repository.toggleFavorite(restaurantId);
    }
}
