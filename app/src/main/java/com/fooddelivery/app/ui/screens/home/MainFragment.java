package com.fooddelivery.app.ui.screens.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.ui.adapters.RestaurantAdapter;
import com.fooddelivery.app.ui.viewmodels.RestaurantViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页 Fragment - Java + XML 版本
 */
public class MainFragment extends Fragment implements RestaurantAdapter.OnItemClickListener {
    
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerRestaurants;
    private ImageView btnSearch;
    private RestaurantViewModel viewModel;
    private RestaurantAdapter adapter;
    private NavController navController;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // 初始化导航
        navController = Navigation.findNavController(view);
        
        // 初始化视图
        swipeRefresh = view.findViewById(R.id.swipe_refresh);
        recyclerRestaurants = view.findViewById(R.id.recycler_restaurants);
        btnSearch = view.findViewById(R.id.btn_search);
        
        // 设置分类图标和名称
        setupCategories(view);
        
        // 设置 RecyclerView
        adapter = new RestaurantAdapter();
        adapter.setOnItemClickListener(this);
        recyclerRestaurants.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRestaurants.setAdapter(adapter);
        
        // 初始化 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(RestaurantViewModel.class);
        
        // 观察数据
        viewModel.getAllRestaurants().observe(getViewLifecycleOwner(), restaurants -> {
            if (restaurants != null) {
                adapter.setRestaurants(restaurants);
                swipeRefresh.setRefreshing(false);
            }
        });
        
        // 下拉刷新
        swipeRefresh.setOnRefreshListener(() -> {
            viewModel.getAllRestaurants();
        });
        
        // 搜索按钮点击
        btnSearch.setOnClickListener(v -> {
            navController.navigate(R.id.action_main_fragment_to_search_fragment);
        });
    }
    
    @Override
    public void onItemClick(Restaurant restaurant) {
        // 跳转到餐厅详情页，只传递 ID
        Bundle bundle = new Bundle();
        bundle.putLong("restaurant_id", restaurant.getId());
        navController.navigate(R.id.action_mainFragment_to_restaurantDetailFragment, bundle);
    }
    
    @Override
    public void onFavoriteClick(Restaurant restaurant, int position) {
        viewModel.toggleFavorite(restaurant.getId());
    }
    
    /**
     * 设置分类图标和名称
     */
    private void setupCategories(View view) {
        // 分类数据：图标和名称
        int[] categoryIcons = {
            R.drawable.category_burger,    // 汉堡美食
            R.drawable.category_pizza,     // 披萨意面
            R.drawable.category_chicken,   // 炸鸡小吃
            R.drawable.category_coffee,    // 咖啡饮品
            R.drawable.category_noodles,   // 面食简餐
            R.drawable.category_dessert    // 甜品饮品
        };
        
        String[] categoryNames = {
            "汉堡美食",
            "披萨意面",
            "炸鸡小吃",
            "咖啡饮品",
            "面食简餐",
            "甜品饮品"
        };
        
        // 查找所有包含分类图标的 LinearLayout
        LinearLayout categoryContainer = findCategoryContainer(view);
        
        if (categoryContainer != null) {
            // 遍历所有子视图（分类项）
            for (int i = 0; i < categoryContainer.getChildCount() && i < categoryIcons.length; i++) {
                View categoryItem = categoryContainer.getChildAt(i);
                ImageView icon = categoryItem.findViewById(R.id.icon_category);
                TextView text = categoryItem.findViewById(R.id.text_category);
                
                if (icon != null && text != null) {
                    icon.setImageResource(categoryIcons[i]);
                    text.setText(categoryNames[i]);
                }
            }
        }
    }
    
    /**
     * 查找包含分类项的容器
     */
    private LinearLayout findCategoryContainer(View view) {
        // 递归查找包含分类图标的 LinearLayout
        if (view instanceof LinearLayout) {
            LinearLayout layout = (LinearLayout) view;
            // 检查这个 LinearLayout 是否包含分类项
            if (layout.getChildCount() > 0) {
                View firstChild = layout.getChildAt(0);
                ImageView icon = firstChild.findViewById(R.id.icon_category);
                if (icon != null) {
                    return layout;
                }
            }
        }
        
        // 递归查找子视图
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                View child = group.getChildAt(i);
                LinearLayout result = findCategoryContainer(child);
                if (result != null) {
                    return result;
                }
            }
        }
        
        return null;
    }
}
