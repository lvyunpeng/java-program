package com.admin.interfaces.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
//        model.addAttribute("list", menuService.list());
        return "statistic/show";
    }

}
