package com.fooddelivery.app.data.repository;

import com.fooddelivery.app.data.mock.MockDataGenerator;
import com.fooddelivery.app.data.model.Banner;

import java.util.List;

/**
 * Banner Repository
 */
public class BannerRepository {
    
    private static BannerRepository instance;
    
    public static synchronized BannerRepository getInstance() {
        if (instance == null) {
            instance = new BannerRepository();
        }
        return instance;
    }
    
    /**
     * Get all banners
     */
    public List<Banner> getAllBanners() {
        return MockDataGenerator.generateBanners();
    }
}
