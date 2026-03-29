package com.fooddelivery.app.data.model;

public class Category {
    private long id;
    private String name;
    private String iconUrl;
    private CategoryType type;

    public Category(long id, String name, String iconUrl, CategoryType type) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.type = type != null ? type : CategoryType.FOOD;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    
    public CategoryType getType() { return type; }
    public void setType(CategoryType type) { this.type = type; }
}
