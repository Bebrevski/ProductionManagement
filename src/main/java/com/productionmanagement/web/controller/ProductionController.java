package com.productionmanagement.web.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.productionmanagement.domain.models.production.ProductionBindingModel;
import com.productionmanagement.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ResponseBody
@RequestMapping("/production")
public class ProductionController extends BaseController{
    private final ProductionService productionService;

    @Autowired
    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping("/create")
    public ModelAndView createProduction() {
        return super.view("production/add-production");
    }

    @PostMapping("/create")
    public ModelAndView submitProduction(@RequestBody ProductionBindingModel model) {
        return super.view("production/add-production");
    }
}
