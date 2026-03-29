package com.fooddelivery.app.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "addresses")
public class Address {
    @PrimaryKey(autoGenerate = true) 
    private long id;
    private String name;
    private String phone;
    private String detailAddress;
    private String province;
    private String city;
    private String district;
    private String street;
    private String buildingInfo;
    private String floorInfo;
    private String doorplateInfo;
    private String addressTag;
    private boolean isDefault;
    private Double latitude;
    private Double longitude;
    
    // 无参构造函数 (Room 需要)
    public Address() {}
    
    // 完整构造函数
    public Address(long id, String name, String phone, String detailAddress,
                   String province, String city, String district, String street,
                   String buildingInfo, String floorInfo, String doorplateInfo,
                   String addressTag, boolean isDefault, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.detailAddress = detailAddress;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
        this.buildingInfo = buildingInfo;
        this.floorInfo = floorInfo;
        this.doorplateInfo = doorplateInfo;
        this.addressTag = addressTag;
        this.isDefault = isDefault;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getDetailAddress() { return detailAddress; }
    public void setDetailAddress(String detailAddress) { this.detailAddress = detailAddress; }
    
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    
    public String getBuildingInfo() { return buildingInfo; }
    public void setBuildingInfo(String buildingInfo) { this.buildingInfo = buildingInfo; }
    
    public String getFloorInfo() { return floorInfo; }
    public void setFloorInfo(String floorInfo) { this.floorInfo = floorInfo; }
    
    public String getDoorplateInfo() { return doorplateInfo; }
    public void setDoorplateInfo(String doorplateInfo) { this.doorplateInfo = doorplateInfo; }
    
    public String getAddressTag() { return addressTag; }
    public void setAddressTag(String addressTag) { this.addressTag = addressTag; }
    
    public boolean isDefault() { return isDefault; }
    public void setDefault(boolean aDefault) { isDefault = aDefault; }
    
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    
    // 辅助方法，获取完整地址
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (province != null) {
            sb.append(province);
        }
        if (city != null) {
            sb.append(city);
        }
        if (district != null) {
            sb.append(district);
        }
        if (street != null) {
            sb.append(street);
        }
        if (detailAddress != null) {
            sb.append(" ").append(detailAddress);
        }
        if (buildingInfo != null && !buildingInfo.isEmpty()) {
            sb.append(" ").append(buildingInfo);
        }
        if (floorInfo != null && !floorInfo.isEmpty()) {
            sb.append(" ").append(floorInfo);
        }
        if (doorplateInfo != null && !doorplateInfo.isEmpty()) {
            sb.append(" ").append(doorplateInfo);
        }
        return sb.toString().trim();
    }
}
