package com.fooddelivery.app.ui.screens.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Dish;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.ui.adapters.DishAdapter;
import com.fooddelivery.app.ui.adapters.RestaurantAdapter;
import com.fooddelivery.app.ui.viewmodels.DishViewModel;
import com.fooddelivery.app.ui.viewmodels.RestaurantViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索页面 Fragment
 */
public class SearchFragment extends Fragment implements RestaurantAdapter.OnItemClickListener {
    
    private EditText editSearch;
    private ImageView btnBack;
    private ImageView btnClear;
    private RecyclerView recyclerRestaurants;
    private RecyclerView recyclerDishes;
    private TextView textRestaurantTitle;
    private TextView textDishTitle;
    
    private RestaurantViewModel restaurantViewModel;
    private DishViewModel dishViewModel;
    private RestaurantAdapter restaurantAdapter;
    private DishAdapter dishAdapter;
    
    private List<Restaurant> allRestaurants = new ArrayList<>();
    private List<Dish> allDishes = new ArrayList<>();
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // 初始化视图
        editSearch = view.findViewById(R.id.edit_search);
        btnBack = view.findViewById(R.id.btn_back);
        btnClear = view.findViewById(R.id.btn_clear);
        recyclerRestaurants = view.findViewById(R.id.recycler_restaurants);
        recyclerDishes = view.findViewById(R.id.recycler_dishes);
        textRestaurantTitle = view.findViewById(R.id.text_restaurant_title);
        textDishTitle = view.findViewById(R.id.text_dish_title);
        
        // 初始隐藏标题和列表
        textRestaurantTitle.setVisibility(View.GONE);
        recyclerRestaurants.setVisibility(View.GONE);
        textDishTitle.setVisibility(View.GONE);
        recyclerDishes.setVisibility(View.GONE);
        
        // 设置餐厅列表
        restaurantAdapter = new RestaurantAdapter();
        restaurantAdapter.setOnItemClickListener(this);
        recyclerRestaurants.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRestaurants.setAdapter(restaurantAdapter);
        
        // 设置菜品列表
        dishAdapter = new DishAdapter();
        recyclerDishes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDishes.setAdapter(dishAdapter);
        
        // 初始化 ViewModel
        restaurantViewModel = new ViewModelProvider(requireActivity()).get(RestaurantViewModel.class);
        dishViewModel = new ViewModelProvider(requireActivity()).get(DishViewModel.class);
        
        // 加载所有餐厅数据
        restaurantViewModel.getAllRestaurants().observe(getViewLifecycleOwner(), restaurants -> {
            if (restaurants != null) {
                allRestaurants = restaurants;
                // 同时加载所有菜品
                loadAllDishes();
            }
        });
        
        // 清空按钮点击
        btnClear.setOnClickListener(v -> {
            editSearch.setText("");
            btnClear.setVisibility(View.GONE);
        });
        
        // 返回按钮点击
        btnBack.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(view);
            navController.navigateUp();
        });
        
        // 搜索框文本变化监听
        editSearch.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                
                // 显示/隐藏清除按钮
                btnClear.setVisibility(query.isEmpty() ? View.GONE : View.VISIBLE);
                
                if (query.isEmpty()) {
                    textRestaurantTitle.setVisibility(View.GONE);
                    recyclerRestaurants.setVisibility(View.GONE);
                    textDishTitle.setVisibility(View.GONE);
                    recyclerDishes.setVisibility(View.GONE);
                } else {
                    performSearch(query);
                }
            }
            
            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });
    }
    
    /**
     * Load all dishes from all restaurants
     */
    private void loadAllDishes() {
        allDishes.clear();
        for (Restaurant restaurant : allRestaurants) {
            dishViewModel.getDishesByRestaurant(restaurant.getId()).observe(getViewLifecycleOwner(), dishes -> {
                if (dishes != null) {
                    // Remove old dishes from this restaurant
                    allDishes.removeIf(dish -> dish.getRestaurantId() == restaurant.getId());
                    // Add new dishes
                    allDishes.addAll(dishes);
                    
                    // Re-perform search if there's a query
                    String query = editSearch.getText().toString().trim();
                    if (!query.isEmpty()) {
                        performSearch(query);
                    }
                }
            });
        }
    }
    
    /**
     * Perform search with the given query
     */
    private void performSearch(String query) {
        String lowerQuery = query.toLowerCase();
        
        // Search restaurants
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : allRestaurants) {
            // Support searching by name, English name, description, and categories
            if ((restaurant.getName() != null && restaurant.getName().toLowerCase().contains(lowerQuery)) || 
                (restaurant.getEnglishName() != null && restaurant.getEnglishName().toLowerCase().contains(lowerQuery)) ||
                (restaurant.getDescription() != null && restaurant.getDescription().toLowerCase().contains(lowerQuery)) ||
                (restaurant.getCategories() != null && restaurant.getCategories().toLowerCase().contains(lowerQuery))) {
                filteredRestaurants.add(restaurant);
            }
        }
        
        if (!filteredRestaurants.isEmpty()) {
            textRestaurantTitle.setVisibility(View.VISIBLE);
            recyclerRestaurants.setVisibility(View.VISIBLE);
            restaurantAdapter.setRestaurants(filteredRestaurants);
        } else {
            textRestaurantTitle.setVisibility(View.GONE);
            recyclerRestaurants.setVisibility(View.GONE);
        }
        
        // Search dishes
        List<Dish> filteredDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            if ((dish.getName() != null && dish.getName().toLowerCase().contains(lowerQuery)) || 
                (dish.getDescription() != null && dish.getDescription().toLowerCase().contains(lowerQuery))) {
                filteredDishes.add(dish);
            }
        }
        
        if (!filteredDishes.isEmpty()) {
            textDishTitle.setVisibility(View.VISIBLE);
            recyclerDishes.setVisibility(View.VISIBLE);
            dishAdapter.setDishes(filteredDishes);
        } else {
            textDishTitle.setVisibility(View.GONE);
            recyclerDishes.setVisibility(View.GONE);
        }
    }
    
    @Override
    public void onItemClick(Restaurant restaurant) {
        // 跳转到餐厅详情页
        Bundle bundle = new Bundle();
        bundle.putLong("restaurant_id", restaurant.getId());
        androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireView());
        navController.navigate(R.id.restaurantDetailFragment, bundle);
    }
    
    @Override
    public void onFavoriteClick(Restaurant restaurant, int position) {
        restaurantViewModel.toggleFavorite(restaurant.getId());
    }
}
