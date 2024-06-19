package org.example.back.controller;


import org.example.back.domain.WmsContainer;
import org.example.back.service.WmsContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
@RestController
@RequestMapping("/wmsContainer")
public class WmsContainerController {

    @Autowired
    private WmsContainerService wmsContainerService;

    @GetMapping("/containerCount")
    public List<Integer> getContainerCount(@RequestBody WmsContainer wmsContainer) {
        String itemNo=wmsContainer.getItemNo();
        String supplier=wmsContainer.getSupplier();
        return wmsContainerService.getDistinctContainerCount(itemNo, supplier);
    }

}

