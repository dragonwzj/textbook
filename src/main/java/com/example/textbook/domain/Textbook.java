package com.example.textbook.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 *教材实体类
 */
@Entity
@Table(name = "tb_textbook")
public class Textbook {

    @Id
    @GeneratedValue
    private Integer textbookCode;

    private String textbookName;

    private Double price;

    @OneToMany(mappedBy = "textbook")
    private List<Store> storeList = new ArrayList<>();

    @OneToMany(mappedBy = "textbook")
    private List<Purchase> purchaseList = new ArrayList<>();

    @OneToMany(mappedBy = "textbook")
    private List<Distribution> distributionList = new ArrayList<>();
    //教材库存量
    private Integer num;

    public Textbook() {
        this.num = 0;
    }

    public Textbook(String textbookName, Double price) {
        this.textbookName = textbookName;
        this.price = price;
        this.num = 0;
    }

    public Integer getTextbookCode() {
        return textbookCode;
    }

    public void setTextbookCode(Integer textbookCode) {
        this.textbookCode = textbookCode;
    }

    public String getTextbookName() {
        return textbookName;
    }

    public void setTextbookName(String textbookName) {
        this.textbookName = textbookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public List<Distribution> getDistributionList() {
        return distributionList;
    }

    public void setDistributionList(List<Distribution> distributionList) {
        this.distributionList = distributionList;
    }
}
