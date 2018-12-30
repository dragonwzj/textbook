package com.example.textbook.domain;

import javax.persistence.*;
import java.util.Date;

/*
 *分发记录实体类
 */
@Entity
@Table(name = "tb_distribution")
public class Distribution {

    @Id
    @GeneratedValue
    private Integer orderId;

    @ManyToOne
    private Textbook textbook;

    @ManyToOne
    private Classes classes;

    @ManyToOne
    private Repository repository;

    private Integer num;

    private Date distributeTime;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(Date distributeTime) {
        this.distributeTime = distributeTime;
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

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
