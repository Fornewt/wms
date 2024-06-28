package org.example.back.controller;


import org.example.back.domain.WmsInventory;
import org.example.back.mapper.WmsInventoryMapper;
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
@RequestMapping("/wmsInventory")
public class WmsInventoryController {
    @Autowired
    private WmsInventoryMapper wmsInventoryMapper;


@GetMapping("/getInventory")
    public List<WmsInventory> getInventory() {
    return wmsInventoryMapper.selectList(null);
}
}

