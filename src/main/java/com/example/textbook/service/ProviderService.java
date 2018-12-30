package com.example.textbook.service;

import com.example.textbook.domain.Provider;
import com.example.textbook.domain.Purchase;
import com.example.textbook.domain.Store;
import com.example.textbook.domain.Textbook;
import com.example.textbook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Transactional
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private TextbookRepository textbookRepository;

    public List<Provider> findAllProvider() {
        return providerRepository.findAll();
    }

    public Provider getProviderByProviderCode(Integer code) {
        return providerRepository.findProviderByProviderCode(code);
    }

    public boolean createProvider(Provider provider) {
        try {
            providerRepository.save(provider);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Purchase> getPurchaseBookOrderByProviderCode(Integer code) {
        Provider provider = providerRepository.findProviderByProviderCode(code);
        return provider.getPurchaseList();
    }

    public boolean buyAndStore(Purchase purchase, Store store) {
        try {
            purchaseRepository.save(purchase);
            storeRepository.save(store);
            //修改教材的库存
            Textbook textbook = purchase.getTextbook();
            textbook.setNum(textbook.getNum() + store.getNum());
            textbookRepository.save(textbook);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;

    }
}
