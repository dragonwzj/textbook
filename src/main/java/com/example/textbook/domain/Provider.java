package com.example.textbook.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 *供应商实体类
 */
@Entity
@Table(name = "tb_provider")
public class Provider {
    @Id
    @GeneratedValue
    private Integer providerCode;

    private String providerName;

    private String tel;

    @OneToMany(mappedBy = "provider")
    private List<Purchase> purchaseList = new ArrayList<>();

    public Provider() {
    }

    public Provider(String providerName, String tel) {
        this.providerName = providerName;
        this.tel = tel;
    }

    public Integer getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(Integer providerCode) {
        this.providerCode = providerCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }
}
