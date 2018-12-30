package com.example.textbook.web;

import com.example.textbook.domain.*;
import com.example.textbook.service.ProviderService;
import com.example.textbook.service.RepositoryService;
import com.example.textbook.service.TextbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private TextbookService textbookService;

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping(path = "provider")
    public ModelAndView provider() {
        ModelAndView modelAndView = new ModelAndView("provider");
        modelAndView.addObject("providers", providerService.findAllProvider());
        return modelAndView;
    }

    @RequestMapping(path = "provider_add")
    public ModelAndView providerAdd() {
        return new ModelAndView("provider_add");
    }

    @PostMapping(path = "do_provider_add")
    public String doProviderAdd(Provider provider) {
        if (providerService.createProvider(provider)) {
            return "redirect:/provider";
        } else {
            return "redirect:/provider_add";
        }
    }

    @RequestMapping(path = "buy_order")
    public ModelAndView buyOrder(Integer providerCode) {
        ModelAndView modelAndView = new ModelAndView("provider_buy_order");
        modelAndView.addObject("provider", providerService.getProviderByProviderCode(providerCode));
        modelAndView.addObject("orders", providerService.getPurchaseBookOrderByProviderCode(providerCode));
        return modelAndView;
    }

    @RequestMapping(path = "buy")
    public ModelAndView buy(Integer providerCode) {
        ModelAndView modelAndView = new ModelAndView("provider_book_buy");
        modelAndView.addObject("provider", providerService.getProviderByProviderCode(providerCode));
        modelAndView.addObject("books", textbookService.findAllTextBook());
        modelAndView.addObject("repositories", repositoryService.findAllRepository());
        return modelAndView;
    }

    @PostMapping(path = "do_buy_store")
    public String doBuyAndStore(Purchase purchase, Integer providerCode, Integer textbookCode, Integer repositoryCode) {
        Provider provider = providerService.getProviderByProviderCode(providerCode);
        Textbook textbook = textbookService.findTextbookByTextbookCode(textbookCode);
        Repository repository = repositoryService.findRepositoryByRepositoryCode(repositoryCode);
        purchase.setProvider(provider);
        purchase.setTextbook(textbook);
        purchase.setRepository(repository);
        purchase.setPurchaseTime(new Date());

        Store store = new Store("入库");
        store.setTextbook(textbook);
        store.setRepository(repository);
        store.setNum(purchase.getNum());
        store.setStoreTime(new Date());

        if (providerService.buyAndStore(purchase, store)) {
            return "redirect:/buy_order?providerCode=" + providerCode;
        } else {
            return "redirect:/buy";
        }
    }
}
