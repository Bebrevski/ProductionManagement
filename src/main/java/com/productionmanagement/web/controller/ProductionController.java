package com.productionmanagement.web.controller;

import com.productionmanagement.domain.models.production.ProductionBindingModel;
import com.productionmanagement.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/production")
public class ProductionController extends BaseController{
    private final ProductionService productionService;

    @Autowired
    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping("/create")
    public ModelAndView createProduction(ProductionBindingModel model) {
        return super.view("production/add-production");
    }
}
