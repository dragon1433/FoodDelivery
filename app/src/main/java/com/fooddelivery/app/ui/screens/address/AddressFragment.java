package com.fooddelivery.app.ui.screens.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.Address;
import com.fooddelivery.app.ui.adapters.AddressAdapter;
import com.fooddelivery.app.ui.viewmodels.AddressViewModel;
import java.util.List;

/**
 * 地址管理 Fragment
 */
public class AddressFragment extends Fragment implements AddressAdapter.OnItemClickListener {
    
    private ImageView btnBack;
    private ImageView btnAdd;
    private RecyclerView recyclerAddresses;
    
    private AddressViewModel viewModel;
    private AddressAdapter adapter;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_address, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // 初始化视图
        btnBack = view.findViewById(R.id.btn_back);
        btnAdd = view.findViewById(R.id.btn_add);
        recyclerAddresses = view.findViewById(R.id.recycler_addresses);
        
        // 设置返回按钮
        btnBack.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        
        // 添加地址按钮
        btnAdd.setOnClickListener(v -> {
            showAddAddressDialog();
        });
        
        // 设置 RecyclerView
        adapter = new AddressAdapter();
        adapter.setOnItemClickListener(this);
        recyclerAddresses.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAddresses.setAdapter(adapter);
        
        // 初始化 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);
        
        // 观察地址数据
        viewModel.getAddresses().observe(getViewLifecycleOwner(), addresses -> {
            if (addresses != null) {
                adapter.setAddresses(addresses);
            }
        });
    }
    
    @Override
    public void onItemClick(Address address) {
        // 删除旧地址并重新添加（清除省市区信息）
        if (address.getProvince() != null || address.getCity() != null || 
            address.getDistrict() != null || address.getStreet() != null) {
            // 清除省市区街道信息
            address.setProvince(null);
            address.setCity(null);
            address.setDistrict(null);
            address.setStreet(null);
            viewModel.updateAddress(address);
            Toast.makeText(getContext(), "已清除地址前缀", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "已选择：" + address.getDetailAddress(), Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    public void onEditClick(Address address) {
        // 编辑地址
        showEditAddressDialog(address);
    }
    
    // 显示添加地址对话框
    private void showAddAddressDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("添加收货地址");
        
        // 创建布局
        android.widget.LinearLayout layout = new android.widget.LinearLayout(getContext());
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);
        
        // 姓名输入框
        final android.widget.EditText nameInput = new android.widget.EditText(getContext());
        nameInput.setHint("收货人姓名");
        layout.addView(nameInput);
        
        // 电话输入框
        final android.widget.EditText phoneInput = new android.widget.EditText(getContext());
        phoneInput.setHint("手机号码");
        layout.addView(phoneInput);
        
        // 地址输入框
        final android.widget.EditText addressInput = new android.widget.EditText(getContext());
        addressInput.setHint("详细地址");
        layout.addView(addressInput);
        
        builder.setView(layout);
        
        // 取消按钮
        builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
        
        // 确定按钮
        builder.setPositiveButton("保存", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String address = addressInput.getText().toString().trim();
            
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(getContext(), "请填写完整信息", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // 创建新地址
            Address newAddress = new Address();
            newAddress.setId(System.currentTimeMillis());
            newAddress.setName(name);
            newAddress.setPhone(phone);
            newAddress.setDetailAddress(address);
            newAddress.setLatitude(22.5428);
            newAddress.setLongitude(114.0543);
            
            // 保存到数据库
            viewModel.addAddress(newAddress);
            
            Toast.makeText(getContext(), "地址添加成功", Toast.LENGTH_SHORT).show();
        });
        
        builder.show();
    }
    
    // 显示编辑地址对话框
    private void showEditAddressDialog(Address address) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("编辑收货地址");
        
        android.widget.LinearLayout layout = new android.widget.LinearLayout(getContext());
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);
        
        final android.widget.EditText nameInput = new android.widget.EditText(getContext());
        nameInput.setText(address.getName());
        nameInput.setHint("收货人姓名");
        layout.addView(nameInput);
        
        final android.widget.EditText phoneInput = new android.widget.EditText(getContext());
        phoneInput.setText(address.getPhone());
        phoneInput.setHint("手机号码");
        layout.addView(phoneInput);
        
        final android.widget.EditText addressInput = new android.widget.EditText(getContext());
        addressInput.setText(address.getDetailAddress());
        addressInput.setHint("详细地址");
        layout.addView(addressInput);
        
        builder.setView(layout);
        
        builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
        
        builder.setPositiveButton("保存", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String addr = addressInput.getText().toString().trim();
            
            if (name.isEmpty() || phone.isEmpty() || addr.isEmpty()) {
                Toast.makeText(getContext(), "请填写完整信息", Toast.LENGTH_SHORT).show();
                return;
            }
            
            address.setName(name);
            address.setPhone(phone);
            address.setDetailAddress(addr);
            
            viewModel.updateAddress(address);
            
            Toast.makeText(getContext(), "地址已更新", Toast.LENGTH_SHORT).show();
        });
        
        builder.show();
    }
}
