package com.example.textbook.domain;

import javax.persistence.*;
import java.util.Date;

/*
 *存储记录实体类
 */
@Entity
@Table(name = "tb_store")
public class Store {

    @Id
    @GeneratedValue
    private Integer orderId;

    @ManyToOne
    private Textbook textbook;

    @ManyToOne
    private Repository repository;

    //操作类型：出库or入库
    private String type;

    private Integer num;

    private Date storeTime;

    public Store() {
    }

    public Store(String type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
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

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
