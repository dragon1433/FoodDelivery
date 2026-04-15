package com.fooddelivery.app.ui.screens.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.ui.adapters.OrderDetailItemAdapter;
import com.fooddelivery.app.ui.viewmodels.OrderViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OrderDetailFragment extends Fragment {
    
    private static final String ARG_ORDER = "arg_order";
    
    private TextView textOrderStatus;
    private TextView textOrderTime;
    private TextView textOrderNo;
    private TextView textRestaurantName;
    private TextView textReceiverInfo;
    private TextView textAddress;
    private TextView textEstimatedTime;
    private TextView textSubtotal;
    private TextView textDeliveryFee;
    private TextView textPackingFee;
    private TextView textDiscount;
    private TextView textTotalAmount;
    private TextView textRiderName;
    private TextView textRiderPhone;
    private LinearLayout layoutRiderInfo;
    private RecyclerView recyclerOrderItems;
    private ImageView btnBack;
    
    // 评价相关视图
    private LinearLayout layoutReview;
    private RatingBar ratingBar;
    private TextView textReviewDisplay;
    private EditText editReviewComment;
    private Button btnSubmitReview;
    
    private OrderDetailItemAdapter itemAdapter;
    private OrderViewModel orderViewModel;
    private Order currentOrder;
    
    public static OrderDetailFragment newInstance(Order order) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ORDER, order);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_detail, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // 初始化 ViewModel
        orderViewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        
        // 初始化视图
        initViews(view);
        
        // 获取订单数据
        if (getArguments() != null) {
            currentOrder = (Order) getArguments().getSerializable(ARG_ORDER);
            if (currentOrder != null) {
                displayOrderDetails(currentOrder);
                setupReviewSection();
            }
        }
    }
    
    private void initViews(View view) {
        // 初始化返回按钮
        btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            // 使用 NavController 立即返回，提高响应速度
            androidx.navigation.NavController navController = 
                androidx.navigation.Navigation.findNavController(requireView());
            navController.popBackStack();
        });
        
        textOrderStatus = view.findViewById(R.id.text_order_status);
        textOrderTime = view.findViewById(R.id.text_order_time);
        textOrderNo = view.findViewById(R.id.text_order_no);
        textRestaurantName = view.findViewById(R.id.text_restaurant_name);
        textReceiverInfo = view.findViewById(R.id.text_receiver_info);
        textAddress = view.findViewById(R.id.text_address);
        textEstimatedTime = view.findViewById(R.id.text_estimated_time);
        textSubtotal = view.findViewById(R.id.text_subtotal);
        textDeliveryFee = view.findViewById(R.id.text_delivery_fee);
        textPackingFee = view.findViewById(R.id.text_packing_fee);
        textDiscount = view.findViewById(R.id.text_discount);
        textTotalAmount = view.findViewById(R.id.text_total_amount);
        textRiderName = view.findViewById(R.id.text_rider_name);
        textRiderPhone = view.findViewById(R.id.text_rider_phone);
        layoutRiderInfo = view.findViewById(R.id.layout_rider_info);
        recyclerOrderItems = view.findViewById(R.id.recycler_order_items);
        
        // 初始化评价相关视图
        layoutReview = view.findViewById(R.id.layout_review);
        ratingBar = view.findViewById(R.id.rating_bar);
        textReviewDisplay = view.findViewById(R.id.text_review_display);
        editReviewComment = view.findViewById(R.id.edit_review_comment);
        btnSubmitReview = view.findViewById(R.id.btn_submit_review);
        
        // 设置商品列表
        itemAdapter = new OrderDetailItemAdapter();
        recyclerOrderItems.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerOrderItems.setAdapter(itemAdapter);
    }
    
    private void displayOrderDetails(Order order) {
        // 订单状态
        textOrderStatus.setText(order.getStatus().getDisplayName());
        
        // 订单时间
        if (order.getCreateTime() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            textOrderTime.setText("Order Time: " + sdf.format(order.getCreateTime()));
        } else {
            textOrderTime.setText("Order Time: Unknown");
        }
        
        // 订单号
        textOrderNo.setText("Order No: " + (order.getOrderNo() != null ? order.getOrderNo() : "N/A"));
        
        // 商家名称
        textRestaurantName.setText(order.getRestaurantName() != null ? order.getRestaurantName() : "Unknown Restaurant");
        
        // 收货人信息 - 分行显示更清晰
        StringBuilder receiverInfo = new StringBuilder();
        if (order.getReceiverName() != null && !order.getReceiverName().isEmpty()) {
            receiverInfo.append("Name: ").append(order.getReceiverName());
        }
        if (order.getReceiverPhone() != null && !order.getReceiverPhone().isEmpty()) {
            if (receiverInfo.length() > 0) {
                receiverInfo.append("\n");
            }
            receiverInfo.append("Phone: ").append(order.getReceiverPhone());
        }
        if (receiverInfo.length() == 0) {
            receiverInfo.append("No receiver information");
        }
        textReceiverInfo.setText(receiverInfo.toString());
        
        // 地址 - 添加标签
        String addressText;
        if (order.getAddressDetail() != null && !order.getAddressDetail().isEmpty()) {
            addressText = "Address: " + order.getAddressDetail();
        } else {
            addressText = "No address";
        }
        textAddress.setText(addressText);
        
        // 预计送达时间
        if (order.getEstimatedDeliveryTime() != null && !order.getEstimatedDeliveryTime().isEmpty()) {
            textEstimatedTime.setText("Estimated Delivery: " + order.getEstimatedDeliveryTime());
        } else {
            textEstimatedTime.setVisibility(View.GONE);
        }
        
        // 商品列表
        if (order.getItems() != null && !order.getItems().isEmpty()) {
            itemAdapter.setItems(order.getItems());
        }
        
        // 费用明细
        double subtotal = order.getTotalAmount();
        double deliveryFee = order.getDeliveryFee();
        double packingFee = order.getPackingFee();
        double discount = order.getDiscountAmount();
        double actualAmount = order.getActualAmount();
        
        // 如果actualAmount为0，则重新计算
        if (actualAmount == 0) {
            actualAmount = subtotal + deliveryFee + packingFee - discount;
        }
        
        textSubtotal.setText(String.format("¥%.2f", subtotal));
        textDeliveryFee.setText(String.format("¥%.2f", deliveryFee));
        textPackingFee.setText(String.format("¥%.2f", packingFee));
        
        if (discount > 0) {
            textDiscount.setText("-¥" + String.format("%.2f", discount));
        } else {
            textDiscount.setText("¥0.00");
        }
        
        textTotalAmount.setText(String.format("¥%.2f", actualAmount));
        
        // 骑手信息（如果有）
        if (order.getRiderName() != null && !order.getRiderName().isEmpty()) {
            layoutRiderInfo.setVisibility(View.VISIBLE);
            textRiderName.setText("Rider: " + order.getRiderName());
            if (order.getRiderPhone() != null) {
                textRiderPhone.setText("Phone: " + order.getRiderPhone());
            }
        } else {
            layoutRiderInfo.setVisibility(View.GONE);
        }
    }
    
    private void setupReviewSection() {
        // 安全检查：确保视图已初始化
        if (layoutReview == null || ratingBar == null || textReviewDisplay == null || 
            editReviewComment == null || btnSubmitReview == null) {
            return;
        }
        
        // 如果订单已经有评价，始终显示评价内容（无论订单状态）
        if (currentOrder != null && currentOrder.getRating() > 0) {
            layoutReview.setVisibility(View.VISIBLE);
            
            // 显示评分
            ratingBar.setRating(currentOrder.getRating());
            ratingBar.setEnabled(false); // 已评价则禁用
            
            // 隐藏编辑框，显示只读文本
            editReviewComment.setVisibility(View.GONE);
            textReviewDisplay.setVisibility(View.VISIBLE);
            
            // 显示评价文本
            String reviewComment = currentOrder.getReviewComment();
            if (reviewComment != null && !reviewComment.isEmpty()) {
                textReviewDisplay.setText(reviewComment);
            } else {
                textReviewDisplay.setText("No additional comments");
            }
            
            // 修改按钮状态
            btnSubmitReview.setText(R.string.order_review_submitted);
            btnSubmitReview.setEnabled(false);
            btnSubmitReview.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                android.graphics.Color.parseColor("#CCCCCC")));
        } 
        // 如果没有评价，但订单状态允许评价（已完成或已支付及以上状态）
        else if (currentOrder != null && currentOrder.getStatus() != null) {
            String status = currentOrder.getStatus().toString();
            // 允许评价的状态：PAID, PREPARING, DELIVERING, DELIVERED, COMPLETED
            if (!status.equals("PENDING") && !status.equals("CANCELLED")) {
                layoutReview.setVisibility(View.VISIBLE);
                
                // 显示编辑框，隐藏只读文本
                editReviewComment.setVisibility(View.VISIBLE);
                textReviewDisplay.setVisibility(View.GONE);
                
                // 未评价，设置提交按钮点击事件
                btnSubmitReview.setOnClickListener(v -> submitReview());
            } else {
                layoutReview.setVisibility(View.GONE);
            }
        } else {
            layoutReview.setVisibility(View.GONE);
        }
    }
    
    private void submitReview() {
        // 安全检查
        if (ratingBar == null || editReviewComment == null || btnSubmitReview == null) {
            return;
        }
        
        float rating = ratingBar.getRating();
        String comment = editReviewComment.getText() != null ? 
            editReviewComment.getText().toString().trim() : "";
        
        if (rating == 0) {
            Toast.makeText(getContext(), R.string.order_review_please_rate, Toast.LENGTH_SHORT).show();
            return;
        }
        
        // 更新订单评价信息
        currentOrder.setRating(rating);
        currentOrder.setReviewComment(comment);
        currentOrder.setReviewTime(new Date());
        
        // 保存到数据库
        orderViewModel.updateOrder(currentOrder);
        
        Toast.makeText(getContext(), R.string.order_review_success, Toast.LENGTH_SHORT).show();
        
        // 禁用评价表单
        ratingBar.setEnabled(false);
        editReviewComment.setEnabled(false);
        btnSubmitReview.setText(R.string.order_review_submitted);
        btnSubmitReview.setEnabled(false);
        btnSubmitReview.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
            android.graphics.Color.parseColor("#CCCCCC")));
    }
}
