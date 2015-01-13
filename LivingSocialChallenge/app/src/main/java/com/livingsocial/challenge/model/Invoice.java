package com.livingsocial.challenge.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ale on 1/12/15.
 */

public class Invoice implements Serializable{
    /*
        {
        "merchant_name": "Daugherty-Schneider",
        "title": "Rustic Rubber Pants",
        "description": "Veritatis non eos. Omnis voluptatem nobis porro consequuntur at dolores. Ad dolores omnis sunt voluptas. Qui quam ipsa unde explicabo asperiores illum tenetur. Unde natus voluptatem omnis eos minus voluptatem excepturi. Mollitia est eligendi occaecati explicabo asperiores. Dolor voluptate assumenda rerum. Quibusdam natus praesentium vero eveniet cupiditate inventore. Ut saepe sunt cupiditate doloribus qui rerum excepturi. Consequatur ut modi molestiae. Vero repellendus totam.",
        "image_url": "http://robohash.org/voluptas.quidem.png?size=500x500",
        "price": 94.47
        },
     */

    @SerializedName("merchant_name")
    private String merchantName= "";
    @SerializedName("title")
    private String title = "";
    @SerializedName("description")
    private String description = "";
    @SerializedName("image_url")
    private String imageUrl = "";
    @SerializedName("price")
    private double price  = 0;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
