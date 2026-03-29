package com.fooddelivery.app.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.local.DishDao;
import com.fooddelivery.app.data.model.Dish;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 菜品数据仓库
 */
public class DishRepository {
    
    private final DishDao dishDao;
    private final ExecutorService executor;
    private final MutableLiveData<List<Dish>> dishesByRestaurant = new MutableLiveData<>();
    
    public DishRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        this.dishDao = database.dishDao();
        this.executor = Executors.newSingleThreadExecutor();
    }
    
    public LiveData<List<Dish>> getDishesByRestaurant(long restaurantId) {
        loadDishes(restaurantId);
        return dishesByRestaurant;
    }
    
    private void loadDishes(long restaurantId) {
        executor.execute(() -> {
            List<Dish> dishes = dishDao.getDishesByRestaurant(restaurantId);
            dishesByRestaurant.postValue(dishes);
        });
    }
    
    public void insertDish(Dish dish) {
        executor.execute(() -> {
            dishDao.insert(dish);
            loadDishes(dish.getRestaurantId());
        });
    }
    
    public void updateDish(Dish dish) {
        executor.execute(() -> {
            dishDao.update(dish);
            loadDishes(dish.getRestaurantId());
        });
    }
    
    public void deleteDish(Dish dish) {
        executor.execute(() -> {
            dishDao.delete(dish);
            loadDishes(dish.getRestaurantId());
        });
    }
}
