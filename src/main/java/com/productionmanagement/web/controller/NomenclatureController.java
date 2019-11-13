package com.productionmanagement.web.controller;

import com.productionmanagement.domain.models.nomenclature.NomenclatureMetadataModel;
import com.productionmanagement.domain.models.nomenclature.NomenclatureModel;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/nomenclature")
public class NomenclatureController extends BaseController {

    private final NomenclatureService nomenclatureService;

    @Autowired
    public NomenclatureController(NomenclatureService nomenclatureService) {
        this.nomenclatureService = nomenclatureService;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        return super.view("nomenclature/nomenclature-index");
    }

    @GetMapping("/getNomenclatureHeaders")
    public OperationResult<List<NomenclatureMetadataModel>> getNomenclatureHeaders() {
        return this.nomenclatureService.getNomenclatureHeaders();
    }

    @GetMapping("/getNomenclatureItems/{nomenclatureId}")
    public OperationResult<List<NomenclatureModel>> getNomenclatureItems(@PathVariable int nomenclatureId) {
            return this.nomenclatureService.getNomenclatureItems(nomenclatureId);
    }
}
