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
        
        // 清空按钮点击
        btnClear.setOnClickListener(v -> {
            editSearch.setText("");
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
                if (query.isEmpty()) {
                    recyclerRestaurants.setVisibility(View.GONE);
                    recyclerDishes.setVisibility(View.GONE);
                } else {
                    search(query);
                }
            }
            
            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });
    }
    
    private void search(String query) {
        // 搜索餐厅
        restaurantViewModel.getAllRestaurants().observe(getViewLifecycleOwner(), restaurants -> {
            if (restaurants != null) {
                List<Restaurant> filteredRestaurants = new ArrayList<>();
                for (Restaurant restaurant : restaurants) {
                    // 支持中文名、英文名和描述搜索
                    if (restaurant.getName().contains(query) || 
                        (restaurant.getEnglishName() != null && restaurant.getEnglishName().toLowerCase().contains(query.toLowerCase())) ||
                        restaurant.getDescription().contains(query) ||
                        restaurant.getCategories().contains(query)) {
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
            }
        });
        
        // 搜索菜品 - 遍历所有餐厅的菜品
        restaurantViewModel.getAllRestaurants().observe(getViewLifecycleOwner(), restaurants -> {
            if (restaurants != null) {
                List<Dish> allDishes = new ArrayList<>();
                for (Restaurant restaurant : restaurants) {
                    List<Dish> dishes = dishViewModel.getDishesByRestaurant(restaurant.getId()).getValue();
                    if (dishes != null) {
                        allDishes.addAll(dishes);
                    }
                }
                
                List<Dish> filteredDishes = new ArrayList<>();
                for (Dish dish : allDishes) {
                    if (dish.getName().contains(query) || 
                        dish.getDescription().contains(query)) {
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
        });
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
