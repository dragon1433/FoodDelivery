package com.fooddelivery.app.data.model;

public class Banner {
    private long id;
    private String imageUrl;
    private String title;
    private BannerLinkType linkType;
    private Long linkId;

    public Banner(long id, String imageUrl, String title, BannerLinkType linkType, Long linkId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.linkType = linkType;
        this.linkId = linkId;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public BannerLinkType getLinkType() { return linkType; }
    public void setLinkType(BannerLinkType linkType) { this.linkType = linkType; }
    
    public Long getLinkId() { return linkId; }
    public void setLinkId(Long linkId) { this.linkId = linkId; }
}
