package org.example.back.controller;


import org.example.back.domain.WmsSupplier;
import org.example.back.mapper.WmsSupplierMapper;
import org.example.back.service.WmsSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/wmsSupplier")
public class WmsSupplierController {
    @Autowired
    private WmsSupplierService wmsSupplierService;
    @GetMapping("/getSupplierName") // 返回所有供应商名字
    public List<String> getSupplierName() {
        return wmsSupplierService.getSupplierName();
    }


}

