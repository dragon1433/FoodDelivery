package com.fooddelivery.app.data.local;

import androidx.room.TypeConverter;
import com.fooddelivery.app.data.model.OrderItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Converters {
    
    @TypeConverter
    public String fromStringList(List<String> value) {
        if (value == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.size(); i++) {
            sb.append(value.get(i));
            if (i < value.size() - 1) sb.append("|");
        }
        return sb.toString();
    }
    
    @TypeConverter
    public List<String> toStringList(String value) {
        if (value == null) return null;
        List<String> result = new ArrayList<>();
        String[] parts = value.split("\\|");
        for (String part : parts) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty() && !trimmed.equals("null")) {
                result.add(trimmed);
            }
        }
        return result;
    }
    
    @TypeConverter
    public String fromDoubleList(List<Double> value) {
        if (value == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.size(); i++) {
            sb.append(value.get(i));
            if (i < value.size() - 1) sb.append("|");
        }
        return sb.toString();
    }
    
    @TypeConverter
    public List<Double> toDoubleList(String value) {
        if (value == null) return null;
        List<Double> result = new ArrayList<>();
        String[] parts = value.split("\\|");
        for (String part : parts) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty() && !trimmed.equals("null")) {
                try {
                    result.add(Double.parseDouble(trimmed));
                } catch (NumberFormatException e) {
                    // ignore
                }
            }
        }
        return result;
    }
    
    @TypeConverter
    public Long fromDate(Date value) {
        return value == null ? null : value.getTime();
    }
    
    @TypeConverter
    public Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }
    
    @TypeConverter
    public String fromOrderItemList(List<OrderItem> value) {
        if (value == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.size(); i++) {
            OrderItem item = value.get(i);
            sb.append(item.getDishId())
              .append("|").append(item.getName())
              .append("|").append(item.getPrice())
              .append("|").append(item.getQuantity())
              .append("|").append(item.getPackingFee());
            if (i < value.size() - 1) sb.append("@@");
        }
        return sb.toString();
    }
    
    @TypeConverter
    public List<OrderItem> toOrderItemList(String value) {
        if (value == null) return null;
        List<OrderItem> result = new ArrayList<>();
        String[] parts = value.split("@@");
        for (String part : parts) {
            if (!part.isEmpty()) {
                String[] data = part.split("\\|");
                if (data.length >= 5) {
                    try {
                        OrderItem item = new OrderItem(
                            Long.parseLong(data[0]),
                            data[1],
                            Double.parseDouble(data[2]),
                            Integer.parseInt(data[3]),
                            Double.parseDouble(data[4])
                        );
                        result.add(item);
                    } catch (NumberFormatException e) {
                        // ignore
                    }
                }
            }
        }
        return result;
    }
}
