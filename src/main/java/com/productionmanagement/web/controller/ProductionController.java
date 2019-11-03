package com.productionmanagement.web.controller;

import com.productionmanagement.domain.models.production.ProductionModel;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.service.ProductionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/production")
public class ProductionController extends BaseController {
    private final ProductionService productionService;
    private final ModelMapper mapper;

    @Autowired
    public ProductionController(ProductionService productionService, ModelMapper mapper) {
        this.productionService = productionService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public ModelAndView createProduction() {
        return super.view("production/add-production");
    }

    @PostMapping("/submit")
    public OperationResult<ProductionModel> submitProduction(@RequestBody ProductionModel productionModel) {
        return productionService.createProduction(productionModel);
    }
}
