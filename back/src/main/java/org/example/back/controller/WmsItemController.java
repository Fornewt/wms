package org.example.back.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.back.domain.WmsItem;
import org.example.back.service.WmsItemService;
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
@Api(tags = "物料管理")
@RestController
@RequestMapping("/wmsItem")
public class WmsItemController {
    @Autowired
    private WmsItemService itemService;

//    @ApiOperation(value = "获取所有物料", notes = "返回所有物料的列表")
//    @GetMapping
//    public List<WmsItem> getAllItems() {
//        return itemService.getAllItems();
//    }

    //根据供货商编号来查找对应售卖的所有零件
    @PostMapping("/getItems")
    public List<WmsItem> getItemsBySupplier(@RequestParam String supplier) {
        //返回给前端渲染零件表
        return itemService.getItemsBySupplier(supplier);
    }


    @ApiOperation(value = "通过ID获取物料", notes = "根据ID获取物料的详细信息")
    @GetMapping("/{id}")
    public WmsItem getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @ApiOperation(value = "添加或更新物料", notes = "添加一个新的物料或更新现有物料")
    @PostMapping
    public void saveOrUpdateItem(@RequestBody WmsItem item) {
        itemService.saveOrUpdateItem(item);
    }

    @ApiOperation(value = "删除物料", notes = "根据ID删除物料")
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}

