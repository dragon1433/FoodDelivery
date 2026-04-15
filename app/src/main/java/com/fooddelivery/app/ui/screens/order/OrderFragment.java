package com.fooddelivery.app.ui.screens.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.ui.adapters.OrderAdapter;
import com.fooddelivery.app.ui.viewmodels.OrderViewModel;
import java.util.Date;
import java.util.List;

/**
 * 订单 Fragment
 */
public class OrderFragment extends Fragment implements OrderAdapter.OnItemClickListener, 
    OrderAdapter.OnDeleteClickListener, OrderAdapter.OnReviewClickListener {
    
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
        adapter.setOnDeleteClickListener(this);
        adapter.setOnReviewClickListener(this);
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
        // 跳转到订单详情页
        Bundle bundle = new Bundle();
        bundle.putSerializable("arg_order", order);
        
        // 使用 NavController 进行导航，保持与底部导航栏同步
        androidx.navigation.NavController navController = 
            androidx.navigation.Navigation.findNavController(requireView());
        navController.navigate(R.id.orderDetailFragment, bundle);
    }
    
    @Override
    public void onDeleteClick(Order order, int position) {
        // 显示删除确认对话框
        new AlertDialog.Builder(requireContext())
            .setTitle(R.string.order_delete_confirm_title)
            .setMessage(R.string.order_delete_confirm_message)
            .setPositiveButton(R.string.confirm, (dialog, which) -> {
                // 执行删除操作
                viewModel.deleteOrder(order);
                Toast.makeText(getContext(), R.string.order_delete_success, Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton(R.string.cancel, null)
            .show();
    }
    
    @Override
    public void onReviewClick(Order order, int position) {
        // 只有已支付及之后的订单才能评价(PAID, PREPARING, DELIVERING, DELIVERED, COMPLETED)
        String status = order.getStatus().toString();
        if (status.equals("PENDING") || status.equals("CANCELLED")) {
            Toast.makeText(getContext(), "Only paid orders can be reviewed", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // 如果已经评价过，提示用户
        if (order.getRating() > 0) {
            Toast.makeText(getContext(), "You have already reviewed this order", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // 创建评价对话框
        showReviewDialog(order);
    }
    
    private void showReviewDialog(Order order) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Review Order");
        
        // 创建自定义布局
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);
        
        // 服务评分
        TextView textService = new TextView(getContext());
        textService.setText("Service Rating:");
        textService.setTextSize(14);
        layout.addView(textService);
        
        RatingBar ratingService = new RatingBar(getContext());
        ratingService.setNumStars(5);
        ratingService.setStepSize(1.0f);
        ratingService.setRating(0);
        ratingService.setLayoutParams(new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        layout.addView(ratingService);
        
        // 菜品评分
        TextView textFood = new TextView(getContext());
        textFood.setText("\nFood Rating:");
        textFood.setTextSize(14);
        layout.addView(textFood);
        
        RatingBar ratingFood = new RatingBar(getContext());
        ratingFood.setNumStars(5);
        ratingFood.setStepSize(1.0f);
        ratingFood.setRating(0);
        ratingFood.setLayoutParams(new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        layout.addView(ratingFood);
        
        // 环境评分
        TextView textEnvironment = new TextView(getContext());
        textEnvironment.setText("\nEnvironment Rating:");
        textEnvironment.setTextSize(14);
        layout.addView(textEnvironment);
        
        RatingBar ratingEnvironment = new RatingBar(getContext());
        ratingEnvironment.setNumStars(5);
        ratingEnvironment.setStepSize(1.0f);
        ratingEnvironment.setRating(0);
        ratingEnvironment.setLayoutParams(new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        layout.addView(ratingEnvironment);
        
        // 评价文本框
        TextView textComment = new TextView(getContext());
        textComment.setText("\nYour Comments:");
        textComment.setTextSize(14);
        layout.addView(textComment);
        
        EditText editComment = new EditText(getContext());
        editComment.setHint("Share your experience and suggestions...");
        editComment.setInputType(android.text.InputType.TYPE_CLASS_TEXT | 
                                  android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editComment.setMinLines(3);
        editComment.setMaxLines(5);
        editComment.setGravity(android.view.Gravity.TOP | android.view.Gravity.START);
        layout.addView(editComment);
        
        builder.setView(layout);
        
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        
        builder.setPositiveButton("Submit", (dialog, which) -> {
            float serviceRating = ratingService.getRating();
            float foodRating = ratingFood.getRating();
            float environmentRating = ratingEnvironment.getRating();
            String comment = editComment.getText().toString().trim();
            
            // 检查是否至少有一个评分
            if (serviceRating == 0 && foodRating == 0 && environmentRating == 0) {
                Toast.makeText(getContext(), "Please give at least one rating", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // 计算平均评分
            float totalRating = 0;
            int count = 0;
            if (serviceRating > 0) { totalRating += serviceRating; count++; }
            if (foodRating > 0) { totalRating += foodRating; count++; }
            if (environmentRating > 0) { totalRating += environmentRating; count++; }
            
            float averageRating = count > 0 ? totalRating / count : 0;
            
            // 构建评价内容
            StringBuilder reviewContent = new StringBuilder();
            if (serviceRating > 0) {
                reviewContent.append(String.format("Service: %.1f/5\n", serviceRating));
            }
            if (foodRating > 0) {
                reviewContent.append(String.format("Food: %.1f/5\n", foodRating));
            }
            if (environmentRating > 0) {
                reviewContent.append(String.format("Environment: %.1f/5\n", environmentRating));
            }
            if (!comment.isEmpty()) {
                reviewContent.append("\n").append(comment);
            }
            
            // 更新订单评价信息
            order.setRating(averageRating);
            order.setReviewComment(reviewContent.toString());
            order.setReviewTime(new Date());
            
            // 保存到数据库
            viewModel.updateOrder(order);
            
            Toast.makeText(getContext(), "Review submitted successfully!", Toast.LENGTH_SHORT).show();
            
            // 刷新列表
            adapter.notifyItemChanged(getAdapterPosition(order));
        });
        
        builder.show();
    }
    
    private int getAdapterPosition(Order order) {
        List<Order> orders = viewModel.getOrders().getValue();
        if (orders != null) {
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getId() == order.getId()) {
                    return i;
                }
            }
        }
        return -1;
    }
}
