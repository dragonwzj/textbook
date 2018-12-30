package com.example.textbook;

import com.example.textbook.domain.*;
import com.example.textbook.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextbookApplicationTests {

    @Autowired
    private TextbookRepository textbookRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Autowired
    private DistributionRepository distributionRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void contextLoads() {
    }

    //初始化数据
    @Test
    public void initDataTest() {
        Textbook textbook = new Textbook("C语言程序设计", 20.5);
        textbookRepository.save(textbook);

        Classes classes = new Classes("软件工程1644", 33);
        classesRepository.save(classes);

        Provider provider = new Provider("清华大学出版社", "10086");
        providerRepository.save(provider);

        Repository repository = new Repository("一号仓库","B06-415");
        repositoryRepository.save(repository);
    }

    //购买并入库过程
    @Test
    public void test1() {
        Textbook textbook = textbookRepository.findTextbookByTextbookName("C语言程序设计");
        Provider provider = providerRepository.findProviderByProviderName("清华大学出版社");
        Repository repository = repositoryRepository.findRepositoryByRepositoryName("一号仓库");
        //添加购买记录
        Purchase purchase = new Purchase();
        purchase.setProvider(provider);
        purchase.setTextbook(textbook);
        purchase.setRepository(repository);
        purchase.setNum(98);
        purchase.setPurchaseTime(new Date());
        purchaseRepository.save(purchase);
        //添加入库记录
        Store store = new Store("入库");
        store.setTextbook(textbook);
        store.setRepository(repository);
        store.setNum(purchase.getNum());
        store.setStoreTime(new Date());
        storeRepository.save(store);
        //修改教材的库存
        textbook.setNum(textbook.getNum() + store.getNum());
        textbookRepository.save(textbook);
    }

    //分发教材过程
    @Test
    public void test2() {
        Textbook textbook = textbookRepository.findTextbookByTextbookName("C语言程序设计");
        Classes classes = classesRepository.findClassesByClassName("软件工程1644");
        Repository repository = repositoryRepository.findRepositoryByRepositoryName("一号仓库");

        //添加分发记录
        Distribution distribution = new Distribution();
        distribution.setClasses(classes);
        distribution.setTextbook(textbook);
        distribution.setRepository(repository);
        distribution.setNum(30);
        distribution.setDistributeTime(new Date());
        distributionRepository.save(distribution);

        //添加出库记录
        Store store = new Store("出库");
        store.setNum(distribution.getNum());
        store.setStoreTime(new Date());
        store.setRepository(repository);
        store.setTextbook(textbook);
        storeRepository.save(store);

        //修改教材库存
        textbook.setNum(textbook.getNum() - distribution.getNum());
        textbookRepository.save(textbook);
    }

    //查看某本教材购买及分发记录
    @Test
    @Transactional
    public void test3() {
        Textbook textbook = textbookRepository.findTextbookByTextbookName("C语言程序设计");
        //以下购买记录
        List<Purchase> purchaseList = textbook.getPurchaseList();
        for (Purchase purchase : purchaseList) {
            Date purchaseTime = purchase.getPurchaseTime();
            Integer num = purchase.getNum();
            Provider provider = purchase.getProvider();
            Repository repository = purchase.getRepository();
            System.out.println("供应商:" + provider.getProviderName() + " 送往仓库:" + repository.getRepositoryName() + " 数量:" + num + " 时间:" + purchaseTime);
        }
        //以下分发记录
        List<Distribution> distributionList = textbook.getDistributionList();
        for (Distribution distribution : distributionList) {
            Date distributeTime = distribution.getDistributeTime();
            Integer num = distribution.getNum();
            String className = distribution.getClasses().getClassName();
            Repository repository = distribution.getRepository();
            System.out.println("仓库名:" + repository.getRepositoryName() + " 发往班级:" + className + " 数量:" + num + " 时间:" + distributeTime);
        }
    }

    //查看某本教材出入库信息
    @Test
    @Transactional
    public void test4() {
        Textbook textbook = textbookRepository.findTextbookByTextbookName("C语言程序设计");
        //以下出入库记录
        List<Store> storeList = textbook.getStoreList();
        for (Store store : storeList) {
            Date storeTime = store.getStoreTime();
            Integer storeNum = store.getNum();
            String repositoryName = store.getRepository().getRepositoryName();
            System.out.println("操作类型:" + store.getType() + " 仓库名:" + repositoryName + " 数量:" + storeNum + " 时间:" + storeTime);
        }
    }

    //查看某仓库的出入库信息
    @Test
    @Transactional
    public void test5() {
        Repository repository = repositoryRepository.findRepositoryByRepositoryName("一号仓库");
        List<Store> storeList = repository.getStoreList();
        for (Store store : storeList) {
            Date storeTime = store.getStoreTime();
            Integer storeNum = store.getNum();
            Textbook textbook = store.getTextbook();
            System.out.println("操作类型:" + store.getType() + " 教材名:" + textbook.getTextbookName() + " 数量:" + storeNum + " 时间:" + storeTime);
        }
    }

    //查看某班级的取书记录
    @Test
    @Transactional
    public void test6() {
        Classes classes = classesRepository.findClassesByClassName("软件工程1644");
        List<Distribution> distributionList = classes.getDistributionList();
        for (Distribution distribution : distributionList) {
            Date distributeTime = distribution.getDistributeTime();
            Integer num = distribution.getNum();
            Repository repository = distribution.getRepository();
            Textbook textbook = distribution.getTextbook();
            System.out.println("教材名:" + textbook.getTextbookName() + " 仓库名:" + repository.getRepositoryName() + " 数量:" + num + " 时间:" + distributeTime);
        }
    }

    //查看某供应商的购买记录
    @Test
    @Transactional
    public void test7() {
        Provider provider = providerRepository.findProviderByProviderName("清华大学出版社");
        List<Purchase> purchaseList = provider.getPurchaseList();
        for (Purchase purchase : purchaseList) {
            Date purchaseTime = purchase.getPurchaseTime();
            Integer num = purchase.getNum();
            Textbook textbook = purchase.getTextbook();
            Repository repository = purchase.getRepository();
            System.out.println("教材名:" + textbook.getTextbookName() + " 仓库名:" + repository.getRepositoryName() + " 数量:" + num + " 时间:" + purchaseTime);
        }
    }


}

