package com.example.textbook.web;

import com.example.textbook.domain.Classes;
import com.example.textbook.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @RequestMapping(path = "class")
    public ModelAndView classes() {
        ModelAndView modelAndView = new ModelAndView("class");
        modelAndView.addObject("classes", classesService.getAllClasses());
        return modelAndView;
    }

    @RequestMapping(path = "class_add")
    public ModelAndView classesAdd() {
        ModelAndView modelAndView = new ModelAndView("class_add");
        return modelAndView;
    }

    @PostMapping(path = "do_class_add")
    public String doClassesAdd(Classes classes) {
        if (classesService.createClasses(classes)) {
            return "redirect:/class";
        } else {
            return "redirect:/class_add";
        }
    }

    @RequestMapping(path = "pick_order")
    public ModelAndView PickOrder(@RequestParam Integer classCode) {
        ModelAndView modelAndView = new ModelAndView("class_pick_order");
        modelAndView.addObject("class", classesService.findClassesByClassCode(classCode));
        modelAndView.addObject("orders", classesService.getPickBookOrderByClassCode(classCode));
        return modelAndView;
    }
}
