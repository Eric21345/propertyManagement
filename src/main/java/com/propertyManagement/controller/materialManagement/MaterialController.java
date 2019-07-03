package com.propertyManagement.controller.materialManagement;

import com.propertyManagement.pojo.Consumables;
import com.propertyManagement.pojo.Equipment;
import com.propertyManagement.service.materialManagement.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    //获取消耗品列表
    @SuppressWarnings("unchecked")
    @RequestMapping("getEquipmentListAndConsumablesList")
    @ResponseBody
    public Map getConsumables(){
        Map map = new HashMap();
        List<Equipment> equipmentList = materialService.getEquipment();
        List<Consumables> consumablesList = materialService.getConsumablesList();
        map.put("status", 1);
        map.put("consumablesList", consumablesList);
        map.put("equipmentList", equipmentList);
        return map;
    }

    //获取物料、设备的购买和申请记录，以及物料的

}
