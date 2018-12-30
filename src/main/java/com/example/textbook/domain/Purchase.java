package com.example.textbook.domain;

import javax.persistence.*;
import java.util.Date;

/*
 *购买记录实体类
 */
@Entity
@Table(name = "tb_purchase")
public class Purchase {

    @Id
    @GeneratedValue
    private Integer orderId;

    @ManyToOne
    private Textbook textbook;

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private Repository repository;

    private Integer num;

    private Date purchaseTime;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Textbook getTextbook() {
        return textbook;
    }

    public void setTextbook(Textbook textbook) {
        this.textbook = textbook;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
