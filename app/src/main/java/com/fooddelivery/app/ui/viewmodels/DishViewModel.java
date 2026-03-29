package com.fooddelivery.app.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.fooddelivery.app.data.model.Dish;
import com.fooddelivery.app.data.repository.DishRepository;
import java.util.List;

/**
 * 菜品 ViewModel
 */
public class DishViewModel extends AndroidViewModel {
    
    private final DishRepository repository;
    
    public DishViewModel(@NonNull Application application) {
        super(application);
        repository = new DishRepository(application);
    }
    
    public LiveData<List<Dish>> getDishesByRestaurant(long restaurantId) {
        return repository.getDishesByRestaurant(restaurantId);
    }
}
