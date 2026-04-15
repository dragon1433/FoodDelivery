package com.fooddelivery.app.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String phone;
    private String password;
    private String name;
    private String avatar;
    private long createTime;
    
    public User() {}
    
    @Ignore
    public User(String phone, String password, String name) {
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.createTime = System.currentTimeMillis();
    }
    
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    
    public long getCreateTime() { return createTime; }
    public void setCreateTime(long createTime) { this.createTime = createTime; }
}
