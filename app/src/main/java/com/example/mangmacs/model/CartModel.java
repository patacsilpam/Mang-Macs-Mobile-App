package com.example.mangmacs.model;

import com.google.gson.annotations.SerializedName;

public class CartModel {
    @SerializedName("success")
    private String success;
    @SerializedName("email")
    private String emailCart;
    @SerializedName("id")
    private int productId;
    @SerializedName("productCode")
    private String productCodeCart;
    @SerializedName("productName")
    private String prooductNameCart;
    @SerializedName("variation")
    private String variationCart;
    @SerializedName("fname")
    private String fnameCart;
    @SerializedName("lname")
    private String lnameCart;
    @SerializedName("price")
    private int priceCart;
    @SerializedName("quantity")
    private int quantityCart;
    @SerializedName("totalprice")
    private int totalprice;
    @SerializedName("add_ons")
    private String add_onsCart;
    @SerializedName("totalCart")
    private String totalcart;
    @SerializedName("imageProduct")
    private String imageProduct;

    public CartModel(String success, String emailCart, int productId, String productCodeCart, String prooductNameCart, String variationCart, String fnameCart, String lnameCart, int priceCart, int quantityCart, int totalprice, String add_onsCart, String totalcart, String imageProduct) {
        this.success = success;
        this.emailCart = emailCart;
        this.productId = productId;
        this.productCodeCart = productCodeCart;
        this.prooductNameCart = prooductNameCart;
        this.variationCart = variationCart;
        this.fnameCart = fnameCart;
        this.lnameCart = lnameCart;
        this.priceCart = priceCart;
        this.quantityCart = quantityCart;
        this.totalprice = totalprice;
        this.add_onsCart = add_onsCart;
        this.totalcart = totalcart;
        this.imageProduct = imageProduct;
    }

    public int getProductId() {
        return productId;
    }
    public String getSuccess() {
        return success;
    }

    public String getEmailCart() {
        return emailCart;
    }

    public String getProductCodeCart() {
        return productCodeCart;
    }

    public String getProoductNameCart() {
        return prooductNameCart;
    }

    public String getVariationCart() {
        return variationCart;
    }

    public String getFnameCart() {
        return fnameCart;
    }

    public String getLnameCart() {
        return lnameCart;
    }

    public int getPriceCart() {
        return priceCart;
    }

    public int getQuantityCart() {
        return quantityCart;
    }

    public String getAdd_onsCart() {
        return add_onsCart;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public String getTotalcart() {
        return totalcart;
    }

    public String getImageProduct() {
        return imageProduct;
    }
}
