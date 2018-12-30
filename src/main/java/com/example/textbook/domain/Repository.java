package com.example.textbook.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 *仓库实体类
 */
@Entity
@Table(name = "tb_repository")
public class Repository {

    @Id
    @GeneratedValue
    private Integer repositoryCode;

    private String repositoryName;

    private String repositoryAddress;

    @OneToMany(mappedBy = "repository")
    private List<Store> storeList = new ArrayList<>();

    //某本书在仓库中的存放量，不持久化进数据库
    @Transient
    private Integer bookNum;

    public Repository() {
        this.bookNum = 0;
    }

    public Repository(String repositoryName, String repositoryAddress) {
        this.repositoryName = repositoryName;
        this.repositoryAddress = repositoryAddress;
        this.bookNum = 0;
    }

    public Integer getRepositoryCode() {
        return repositoryCode;
    }

    public void setRepositoryCode(Integer repositoryCode) {
        this.repositoryCode = repositoryCode;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getRepositoryAddress() {
        return repositoryAddress;
    }

    public void setRepositoryAddress(String repositoryAddress) {
        this.repositoryAddress = repositoryAddress;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(repositoryCode, that.repositoryCode) &&
                Objects.equals(repositoryName, that.repositoryName) &&
                Objects.equals(repositoryAddress, that.repositoryAddress) &&
                Objects.equals(storeList, that.storeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repositoryCode, repositoryName, repositoryAddress, storeList);
    }
}
