package com.fooddelivery.app.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.fooddelivery.app.data.model.Dish;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.data.repository.DishRepository;
import com.fooddelivery.app.data.repository.RestaurantRepository;
import java.util.List;

/**
 * 菜品 ViewModel
 */
public class DishViewModel extends AndroidViewModel {
    
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    
    public DishViewModel(@NonNull Application application) {
        super(application);
        dishRepository = new DishRepository(application);
        restaurantRepository = new RestaurantRepository(application);
    }
    
    public LiveData<List<Dish>> getDishesByRestaurant(long restaurantId) {
        return dishRepository.getDishesByRestaurant(restaurantId);
    }
    
    public LiveData<Restaurant> getRestaurantById(long restaurantId) {
        return restaurantRepository.getRestaurantById(restaurantId);
    }
}
