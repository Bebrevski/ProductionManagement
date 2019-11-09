package com.productionmanagement.web.controller;

import com.productionmanagement.domain.models.production.ProductionModel;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.service.ProductionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        return productionService.submitProduction(productionModel);
    }

    @GetMapping("/edit/{uuid}")
    public ModelAndView editProduction() {
        return super.view("production/add-production");
    }

    @GetMapping("/show-all")
    public ModelAndView showAllProductions() {
        return super.view("production/all-productions");
    }

    @GetMapping("/view/{uuid}")
    public ModelAndView productionView(@PathVariable String uuid) {
        String productionName = this.productionService.getProductionData(uuid).ResultData.getName();

        ModelAndView model = new ModelAndView();
        model.addObject("productionViewName", productionName);

        return super.view("production/production-view", model);
    }

    //Get data
    @GetMapping("/get-all")
    public OperationResult<List<ProductionModel>> getAllProductions() {
        return this.productionService.getAllProductions();
    }

    @GetMapping("/get-production-data/{uuid}")
    public OperationResult<ProductionModel> getProductionData(@PathVariable String uuid) {
        return this.productionService.getProductionData(uuid);
    }
    //End
}
