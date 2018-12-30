package com.example.textbook.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 *班级实体类
 */
@Entity
@Table(name = "tb_classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer classCode;

    private String className;

    @OneToMany(mappedBy = "classes")
    private List<Distribution> distributionList = new ArrayList<>();

    //班级人数
    private Integer number;

    public Classes() {
    }

    public Classes(String className, Integer number) {
        this.className = className;
        this.number = number;
    }

    public Integer getClassCode() {
        return classCode;
    }

    public void setClassCode(Integer classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Distribution> getDistributionList() {
        return distributionList;
    }

    public void setDistributionList(List<Distribution> distributionList) {
        this.distributionList = distributionList;
    }
}
