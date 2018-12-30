package com.example.textbook.service;

import com.example.textbook.domain.Distribution;
import com.example.textbook.domain.Repository;
import com.example.textbook.domain.Store;
import com.example.textbook.domain.Textbook;
import com.example.textbook.repository.DistributionRepository;
import com.example.textbook.repository.StoreRepository;
import com.example.textbook.repository.TextbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TextbookService {

    @Autowired
    private DistributionRepository distributionRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private TextbookRepository textbookRepository;

    public List<Textbook> findAllTextBook() {
        return textbookRepository.findAll();
    }

    public Textbook findTextbookByTextbookCode(Integer code) {
        return textbookRepository.findTextbookByTextbookCode(code);
    }

    public boolean createTextbook(Textbook textbook) {
        try {
            textbookRepository.save(textbook);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Repository> getBookRepositoriesByTextbookCode(Integer code) {
        List<Repository> repositories = new ArrayList<>();
        Textbook textbook = textbookRepository.findTextbookByTextbookCode(code);
        List<Store> storeList = textbook.getStoreList();
        for (Store store : storeList) {
            Repository repository = store.getRepository();
            if (!repositories.contains(repository)) {
                repositories.add(repository);
            }
        }
        return repositories;
    }

    public boolean DistributeAndStore(Distribution distribution, Store store) {
        try {
            distributionRepository.save(distribution);
            storeRepository.save(store);
            //修改教材库存
            Textbook textbook = distribution.getTextbook();
            textbook.setNum(textbook.getNum() - distribution.getNum());
            textbookRepository.save(textbook);
            //检查各仓库库存是否出现负
            List<Repository> repositories = findTextbookDistribution(textbook.getTextbookCode());
            for (Repository repository : repositories) {
                if (repository.getBookNum() < 0) {
                    //低于0抛出异常
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //异常触发JPA进行事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    public List<Repository> findTextbookDistribution(Integer code) {
        Textbook textbook = textbookRepository.findTextbookByTextbookCode(code);
        List<Repository> repositories = new ArrayList<>();
        List<Store> storeList = textbook.getStoreList();
        for (Store store : storeList) {
            Repository repository = store.getRepository();
            if (repositories.contains(repository)) {
                int i = repositories.indexOf(repository);
                Repository old = repositories.get(i);
                if (store.getType().equals("入库")) {
                    old.setBookNum(old.getBookNum() + store.getNum());
                } else {
                    old.setBookNum(old.getBookNum() - store.getNum());
                }
            } else {
                if (store.getType().equals("入库")) {
                    repository.setBookNum(repository.getBookNum() + store.getNum());
                } else {
                    repository.setBookNum(repository.getBookNum() - store.getNum());
                }
                repositories.add(repository);
            }
        }
        return repositories;
    }
}
