package com.fooddelivery.app.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.local.RestaurantDao;
import com.fooddelivery.app.data.model.Restaurant;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 餐厅数据仓库
 */
public class RestaurantRepository {
    
    private final RestaurantDao restaurantDao;
    private final ExecutorService executor;
    private final MutableLiveData<List<Restaurant>> allRestaurants = new MutableLiveData<>();
    
    public RestaurantRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        this.restaurantDao = database.restaurantDao();
        this.executor = Executors.newSingleThreadExecutor();
    }
    
    public LiveData<List<Restaurant>> getAllRestaurants() {
        loadRestaurants();
        return allRestaurants;
    }
    
    private void loadRestaurants() {
        executor.execute(() -> {
            List<Restaurant> restaurants = restaurantDao.getAllRestaurants();
            allRestaurants.postValue(restaurants);
        });
    }
    
    public void toggleFavorite(long restaurantId) {
        executor.execute(() -> {
            Restaurant restaurant = restaurantDao.getRestaurantById(restaurantId);
            if (restaurant != null) {
                restaurant.setFavorite(!restaurant.isFavorite());
                restaurantDao.update(restaurant);
                loadRestaurants();
            }
        });
    }
}
