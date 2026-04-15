package com.fooddelivery.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.fooddelivery.app.databinding.ActivityMainBinding;
import com.fooddelivery.app.data.mock.MockDataGenerator;

/**
 * Main Activity - Java + XML Version
 */
public class MainActivity extends AppCompatActivity {
    
    private ActivityMainBinding binding;
    private NavController navController;
    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_FIRST_RUN = "first_run";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        // Initialize Mock data
        MockDataGenerator.generateMockData(this);
        
        // Setup navigation
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
            
            // Listen to navigation changes, hide bottom navigation on login/register pages
            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                if (destination.getId() == R.id.loginFragment || 
                    destination.getId() == R.id.registerFragment ||
                    destination.getId() == R.id.orderDetailFragment) {
                    binding.bottomNavigation.setVisibility(View.GONE);
                } else {
                    binding.bottomNavigation.setVisibility(View.VISIBLE);
                }
            });
        }
    }
    
    // Public method to reset bottom navigation (can be called from fragments)
    public void resetBottomNavigation() {
        if (binding != null && binding.bottomNavigation != null) {
            binding.bottomNavigation.setSelectedItemId(R.id.mainFragment);
        }
    }
}
