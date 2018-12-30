package com.example.textbook.web;

import com.example.textbook.domain.*;
import com.example.textbook.service.ClassesService;
import com.example.textbook.service.RepositoryService;
import com.example.textbook.service.TextbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class TextbookController {

    @Autowired
    private TextbookService textbookService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping(path = "textbook")
    public ModelAndView textbook() {
        ModelAndView modelAndView = new ModelAndView("textbook");
        modelAndView.addObject("textbooks", textbookService.findAllTextBook());
        return modelAndView;
    }

    @RequestMapping(path = "textbook_add")
    public ModelAndView textbookAdd() {
        ModelAndView modelAndView = new ModelAndView("textbook_add");
        return modelAndView;
    }

    @PostMapping(path = "do_textbook_add")
    public String doTextbookAdd(Textbook textbook) {
        if (textbookService.createTextbook(textbook)) {
            return "redirect:/textbook";
        } else {
            return "redirect:/textbook_add";
        }
    }

    @RequestMapping(path = "textbook_distribute_order")
    public ModelAndView textbookDistributeOrder(Integer textbookCode) {
        Textbook textbook = textbookService.findTextbookByTextbookCode(textbookCode);
        ModelAndView modelAndView = new ModelAndView("textbook_distribute_order");
        modelAndView.addObject("textbook", textbook);
        modelAndView.addObject("orders", textbook.getDistributionList());
        return modelAndView;
    }

    @RequestMapping(path = "textbook_distribute")
    public ModelAndView textbookDistribute(Integer textbookCode) {
        Textbook textbook = textbookService.findTextbookByTextbookCode(textbookCode);
        ModelAndView modelAndView = new ModelAndView("textbook_distribute");
        modelAndView.addObject("textbook", textbook);
        modelAndView.addObject("classes", classesService.getAllClasses());
        modelAndView.addObject("repositories", textbookService.getBookRepositoriesByTextbookCode(textbookCode));
        return modelAndView;
    }

    @PostMapping(path = "do_textbook_distribute")
    public String doTextbookDistribute(RedirectAttributes attributes, Distribution distribution, Integer classCode, Integer textbookCode, Integer repositoryCode) {
        Classes classes = classesService.findClassesByClassCode(classCode);
        Textbook textbook = textbookService.findTextbookByTextbookCode(textbookCode);
        Repository repository = repositoryService.findRepositoryByRepositoryCode(repositoryCode);
        distribution.setClasses(classes);
        distribution.setTextbook(textbook);
        distribution.setRepository(repository);
        distribution.setDistributeTime(new Date());

        Store store = new Store("出库");
        store.setNum(distribution.getNum());
        store.setStoreTime(new Date());
        store.setRepository(repository);
        store.setTextbook(textbook);

        if (textbookService.DistributeAndStore(distribution, store)) {
            return "redirect:/textbook_distribute_order?textbookCode=" + textbookCode;
        } else {
            attributes.addFlashAttribute("info", new PageInfo("error", "教材分发失败，请检查库存"));
            return "redirect:/textbook_distribute?textbookCode=" + textbookCode;
        }
    }

    @RequestMapping(path = "textbook_buy_order")
    public ModelAndView textbookBuyOrder(Integer textbookCode) {
        Textbook textbook = textbookService.findTextbookByTextbookCode(textbookCode);
        ModelAndView modelAndView = new ModelAndView("textbook_buy_order");
        modelAndView.addObject("textbook", textbook);
        modelAndView.addObject("orders", textbook.getPurchaseList());
        return modelAndView;
    }

    @RequestMapping(path = "textbook_distribution")
    public ModelAndView textbookDistribution(Integer textbookCode) {
        Textbook textbook = textbookService.findTextbookByTextbookCode(textbookCode);
        ModelAndView modelAndView = new ModelAndView("textbook_distribution");
        modelAndView.addObject("textbook", textbook);
        modelAndView.addObject("repositories", textbookService.findTextbookDistribution(textbookCode));
        return modelAndView;
    }
}
