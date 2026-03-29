package com.fooddelivery.app.ui.screens.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.fooddelivery.app.R;
import com.fooddelivery.app.ui.screens.address.AddressFragment;

import java.util.Random;

/**
 * 个人中心 Fragment
 */
public class ProfileFragment extends Fragment {
    
    private ImageView imageAvatar;
    private TextView textUsername;
    private TextView textPhone;
    private LinearLayout layoutAddress;
    private LinearLayout layoutSettings;
    private LinearLayout layoutMyOrders;
    private LinearLayout layoutShoppingCart;
    private LinearLayout layoutCoupons;
    private LinearLayout layoutVip;
    private LinearLayout layoutCustomerService;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // 初始化视图
        imageAvatar = view.findViewById(R.id.image_avatar);
        textUsername = view.findViewById(R.id.text_username);
        textPhone = view.findViewById(R.id.text_phone);
        layoutAddress = view.findViewById(R.id.layout_address);
        layoutSettings = view.findViewById(R.id.layout_settings);
        layoutMyOrders = view.findViewById(R.id.layout_my_orders);
        layoutShoppingCart = view.findViewById(R.id.layout_shopping_cart);
        layoutCoupons = view.findViewById(R.id.layout_coupons);
        layoutVip = view.findViewById(R.id.layout_vip);
        layoutCustomerService = view.findViewById(R.id.layout_customer_service);
        
        // 生成随机用户信息
        String[] usernames = {"张三", "李四", "王五", "赵六", "钱七", "孙八", "周九", "吴十"};
        String[] phones = {"138****1234", "139****5678", "186****9012", "185****3456", "137****7890"};
        
        Random random = new Random();
        String randomUsername = usernames[random.nextInt(usernames.length)];
        String randomPhone = phones[random.nextInt(phones.length)];
        
        textUsername.setText(randomUsername);
        textPhone.setText(randomPhone);
        
        // 我的订单点击 - 切换到订单 Tab
        layoutMyOrders.setOnClickListener(v -> {
            if (getActivity() != null) {
                // 通过底部导航切换到订单页面
                com.google.android.material.bottomnavigation.BottomNavigationView bottomNav = 
                    getActivity().findViewById(R.id.bottom_navigation);
                bottomNav.setSelectedItemId(R.id.orderFragment);
            }
        });
        
        // 地址管理点击
        layoutAddress.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_profile_fragment_to_address_fragment);
        });
        
        // 购物车点击 - 切换到购物车 Tab
        layoutShoppingCart.setOnClickListener(v -> {
            if (getActivity() != null) {
                // 通过底部导航切换到购物车页面
                com.google.android.material.bottomnavigation.BottomNavigationView bottomNav = 
                    getActivity().findViewById(R.id.bottom_navigation);
                bottomNav.setSelectedItemId(R.id.cartFragment);
            }
        });
        
        // 设置点击
        layoutSettings.setOnClickListener(v -> {
            Toast.makeText(getContext(), "设置功能开发中...", Toast.LENGTH_SHORT).show();
        });
        
        // 优惠券/红包点击
        layoutCoupons.setOnClickListener(v -> {
            Toast.makeText(getContext(), "优惠券功能开发中...", Toast.LENGTH_SHORT).show();
        });
        
        // 会员中心点击
        layoutVip.setOnClickListener(v -> {
            Toast.makeText(getContext(), "会员中心功能开发中...", Toast.LENGTH_SHORT).show();
        });
        
        // 客服与帮助点击
        layoutCustomerService.setOnClickListener(v -> {
            Toast.makeText(getContext(), "客服与帮助功能开发中...", Toast.LENGTH_SHORT).show();
        });
    }
}
