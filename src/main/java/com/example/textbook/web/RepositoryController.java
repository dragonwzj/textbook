package com.example.textbook.web;

import com.example.textbook.domain.Repository;
import com.example.textbook.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RepositoryController {

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping(path = "repository")
    public ModelAndView repository() {
        ModelAndView modelAndView = new ModelAndView("repository");
        modelAndView.addObject("repositories", repositoryService.findAllRepository());
        return modelAndView;
    }

    @RequestMapping(path = "repository_add")
    public ModelAndView repositoryAdd() {
        return new ModelAndView("repository_add");
    }

    @RequestMapping(path = "do_repository_add")
    public String doRepositoryAdd(Repository repository) {
        if (repositoryService.createRepository(repository)) {
            return "redirect:/repository";
        } else {
            return "redirect:/repository_add";
        }
    }

    @RequestMapping(path = "repository_store_order")
    public ModelAndView repositoryStoreOrder(Integer repositoryCode) {
        ModelAndView modelAndView = new ModelAndView("repository_store_order");
        Repository repository = repositoryService.findRepositoryByRepositoryCode(repositoryCode);
        modelAndView.addObject("repository", repository);
        modelAndView.addObject("orders", repository.getStoreList());
        return modelAndView;
    }
}
