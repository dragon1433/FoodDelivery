package com.fooddelivery.app.ui.screens.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import androidx.viewpager2.widget.ViewPager2;
import com.bumptech.glide.Glide;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Banner;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.data.repository.BannerRepository;
import com.fooddelivery.app.ui.adapters.BannerAdapter;
import com.fooddelivery.app.ui.adapters.RestaurantAdapter;
import com.fooddelivery.app.ui.viewmodels.RestaurantViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Home Fragment - Java + XML Version
 */
public class MainFragment extends Fragment implements RestaurantAdapter.OnItemClickListener {
    
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerRestaurants;
    private ImageView btnSearch;
    private ViewPager2 viewPagerBanner;
    private LinearLayout layoutPageIndicator;
    private RestaurantViewModel viewModel;
    private RestaurantAdapter adapter;
    private BannerAdapter bannerAdapter;
    private NavController navController;
    private Handler bannerHandler;
    private Runnable bannerRunnable;
    private List<Banner> bannerList = new ArrayList<>();
    
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
        
        // Initialize navigation
        navController = Navigation.findNavController(view);
        
        // Initialize views
        swipeRefresh = view.findViewById(R.id.swipe_refresh);
        recyclerRestaurants = view.findViewById(R.id.recycler_restaurants);
        btnSearch = view.findViewById(R.id.btn_search);
        viewPagerBanner = view.findViewById(R.id.view_pager_banner);
        layoutPageIndicator = view.findViewById(R.id.layout_page_indicator);
        
        // Setup banner carousel
        setupBannerCarousel();
        
        // Setup category icons and names
        setupCategories(view);
        
        // Setup RecyclerView
        adapter = new RestaurantAdapter();
        adapter.setOnItemClickListener(this);
        recyclerRestaurants.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRestaurants.setAdapter(adapter);
        
        // Initialize ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(RestaurantViewModel.class);
        
        // Observe data
        viewModel.getAllRestaurants().observe(getViewLifecycleOwner(), restaurants -> {
            if (restaurants != null) {
                adapter.setRestaurants(restaurants);
                swipeRefresh.setRefreshing(false);
            }
        });
        
        // Pull to refresh
        swipeRefresh.setOnRefreshListener(() -> {
            viewModel.getAllRestaurants();
        });
        
        // Search button click
        btnSearch.setOnClickListener(v -> {
            navController.navigate(R.id.action_main_fragment_to_search_fragment);
        });
    }
    
    @Override
    public void onItemClick(Restaurant restaurant) {
        // Navigate to restaurant detail page, only pass ID
        Bundle bundle = new Bundle();
        bundle.putLong("restaurant_id", restaurant.getId());
        navController.navigate(R.id.action_mainFragment_to_restaurantDetailFragment, bundle);
    }
    
    @Override
    public void onFavoriteClick(Restaurant restaurant, int position) {
        viewModel.toggleFavorite(restaurant.getId());
    }
    
    /**
     * Setup banner carousel with auto-play
     */
    private void setupBannerCarousel() {
        // Load banners from repository
        bannerList = BannerRepository.getInstance().getAllBanners();
        
        // Initialize adapter
        bannerAdapter = new BannerAdapter();
        bannerAdapter.setBanners(bannerList);
        bannerAdapter.setOnBannerClickListener(this::onBannerClick);
        
        // Setup ViewPager2
        viewPagerBanner.setAdapter(bannerAdapter);
        viewPagerBanner.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        
        // Setup page indicator
        setupPageIndicator();
        
        // Auto-play functionality
        bannerHandler = new Handler(Looper.getMainLooper());
        bannerRunnable = new Runnable() {
            @Override
            public void run() {
                if (bannerList.size() > 1) {
                    int currentItem = viewPagerBanner.getCurrentItem();
                    int nextItem = (currentItem + 1) % bannerList.size();
                    viewPagerBanner.setCurrentItem(nextItem, true);
                    bannerHandler.postDelayed(this, 3000); // Change every 3 seconds
                }
            }
        };
        
        // Start auto-play
        startAutoPlay();
        
        // Pause auto-play when user interacts
        viewPagerBanner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updatePageIndicator(position);
            }
            
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    stopAutoPlay();
                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    startAutoPlay();
                }
            }
        });
    }
    
    /**
     * Setup page indicator dots
     */
    private void setupPageIndicator() {
        layoutPageIndicator.removeAllViews();
        
        for (int i = 0; i < bannerList.size(); i++) {
            ImageView dot = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                getResources().getDimensionPixelSize(android.R.dimen.app_icon_size) / 4,
                getResources().getDimensionPixelSize(android.R.dimen.app_icon_size) / 4
            );
            params.setMargins(8, 0, 8, 0);
            dot.setLayoutParams(params);
            dot.setImageResource(i == 0 ? R.drawable.indicator_selected : R.drawable.indicator_default);
            layoutPageIndicator.addView(dot);
        }
    }
    
    /**
     * Update page indicator based on current position
     */
    private void updatePageIndicator(int position) {
        for (int i = 0; i < layoutPageIndicator.getChildCount(); i++) {
            ImageView dot = (ImageView) layoutPageIndicator.getChildAt(i);
            dot.setImageResource(i == position ? R.drawable.indicator_selected : R.drawable.indicator_default);
        }
    }
    
    /**
     * Start auto-play
     */
    private void startAutoPlay() {
        if (bannerHandler != null && bannerRunnable != null && bannerList.size() > 1) {
            bannerHandler.removeCallbacks(bannerRunnable);
            bannerHandler.postDelayed(bannerRunnable, 3000);
        }
    }
    
    /**
     * Stop auto-play
     */
    private void stopAutoPlay() {
        if (bannerHandler != null && bannerRunnable != null) {
            bannerHandler.removeCallbacks(bannerRunnable);
        }
    }
    
    /**
     * Handle banner click
     */
    private void onBannerClick(Banner banner) {
        // Navigate based on link type
        switch (banner.getLinkType()) {
            case RESTAURANT:
                if (banner.getLinkId() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("restaurant_id", banner.getLinkId());
                    navController.navigate(R.id.action_mainFragment_to_restaurantDetailFragment, bundle);
                }
                break;
            case CATEGORY:
                // TODO: Navigate to category filter
                break;
            case ACTIVITY:
                // Show activity details or promotion
                break;
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();
        startAutoPlay();
    }
    
    @Override
    public void onPause() {
        super.onPause();
        stopAutoPlay();
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopAutoPlay();
        bannerHandler = null;
        bannerRunnable = null;
    }
    
    /**
     * Setup category icons and names
     */
    private void setupCategories(View view) {
        // Category data: real image URLs from Unsplash
        String[] categoryImageUrls = {
            "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=200",  // Burger
            "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=200",  // Pizza & Pasta
            "https://images.unsplash.com/photo-1626645738196-c2a7c87a8f58?w=200",  // Fried Chicken
            "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=200",  // Coffee & Drinks
            "https://images.unsplash.com/photo-1612929633738-8fe44f7ec841?w=200",  // Noodles
            "https://images.unsplash.com/photo-1524351199678-941a58a3df50?w=200"   // Desserts
        };
        
        String[] categoryNames = {
            "Burger",
            "Pizza & Pasta",
            "Fried Chicken",
            "Coffee & Drinks",
            "Noodles",
            "Desserts"
        };
        
        // Find all LinearLayouts containing category items
        LinearLayout categoryContainer = findCategoryContainer(view);
        
        if (categoryContainer != null) {
            // Iterate through all child views (category items)
            for (int i = 0; i < categoryContainer.getChildCount() && i < categoryImageUrls.length; i++) {
                View categoryItem = categoryContainer.getChildAt(i);
                ImageView icon = categoryItem.findViewById(R.id.icon_category);
                TextView text = categoryItem.findViewById(R.id.text_category);
                
                if (icon != null && text != null) {
                    // Load real image using Glide with error handling
                    final int categoryIndex = i;
                    final String currentImageUrl = categoryImageUrls[i];
                    final String currentCategoryName = categoryNames[i];
                    final long currentCategoryId = i + 1; // Category ID starts from 1
                    
                    Glide.with(this)
                        .load(currentImageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.category_coffee)  // Use category icon as placeholder
                        .error(R.drawable.category_coffee)  // Show placeholder on error
                        .into(icon);
                    text.setText(currentCategoryName);
                    
                    // Add click listener to show category detail dialog
                    icon.setOnClickListener(v -> showCategoryDetail(currentCategoryName, currentImageUrl, currentCategoryId));
                    text.setOnClickListener(v -> showCategoryDetail(currentCategoryName, currentImageUrl, currentCategoryId));
                }
            }
        }
    }
    
    /**
     * Find the container that holds category items
     */
    private LinearLayout findCategoryContainer(View view) {
        // Recursively find LinearLayout containing category icons
        if (view instanceof LinearLayout) {
            LinearLayout layout = (LinearLayout) view;
            // Check if this LinearLayout contains category items
            if (layout.getChildCount() > 0) {
                View firstChild = layout.getChildAt(0);
                ImageView icon = firstChild.findViewById(R.id.icon_category);
                if (icon != null) {
                    return layout;
                }
            }
        }
        
        // Recursively search child views
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
    
    /**
     * Show category detail bottom sheet dialog
     */
    private void showCategoryDetail(String categoryName, String categoryImage, long categoryId) {
        com.fooddelivery.app.ui.screens.category.CategoryDetailDialog dialog = 
            com.fooddelivery.app.ui.screens.category.CategoryDetailDialog.newInstance(
                categoryName, categoryImage, categoryId);
        dialog.show(getChildFragmentManager(), "CategoryDetail");
    }
}
