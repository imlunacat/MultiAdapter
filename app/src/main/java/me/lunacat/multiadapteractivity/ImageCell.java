package me.lunacat.multiadapteractivity;

public class ImageCell implements ITableCell{
    private String imageUrl, pageUrl;

    public ImageCell(String imageUrl) {
        this.imageUrl = imageUrl;
        this.pageUrl = imageUrl.replaceAll("\\.jpg$", "");
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getPageUrl() {
        return this.pageUrl;
    }
}