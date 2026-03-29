package com.fooddelivery.app.ui.screens.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.ui.adapters.OrderAdapter;
import com.fooddelivery.app.ui.viewmodels.OrderViewModel;
import java.util.List;

/**
 * 订单 Fragment
 */
public class OrderFragment extends Fragment implements OrderAdapter.OnItemClickListener {
    
    private RecyclerView recyclerOrders;
    private LinearLayout layoutEmpty;
    
    private OrderViewModel viewModel;
    private OrderAdapter adapter;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // 初始化视图
        recyclerOrders = view.findViewById(R.id.recycler_orders);
        layoutEmpty = view.findViewById(R.id.layout_empty);
        
        // 设置 RecyclerView
        adapter = new OrderAdapter();
        adapter.setOnItemClickListener(this);
        recyclerOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerOrders.setAdapter(adapter);
        
        // 初始化 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        
        // 观察订单数据
        viewModel.getOrders().observe(getViewLifecycleOwner(), orders -> {
            if (orders != null && !orders.isEmpty()) {
                adapter.setOrders(orders);
                recyclerOrders.setVisibility(View.VISIBLE);
                layoutEmpty.setVisibility(View.GONE);
            } else {
                recyclerOrders.setVisibility(View.GONE);
                layoutEmpty.setVisibility(View.VISIBLE);
            }
        });
    }
    
    @Override
    public void onItemClick(Order order) {
        // TODO: 跳转到订单详情页
    }
}
