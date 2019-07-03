package com.propertyManagement.mapper.materialManagement;


import com.propertyManagement.pojo.Consumables;
import com.propertyManagement.pojo.Equipment;

import java.util.List;

public interface MaterialMapper {
    //获取消耗品列表
    List<Consumables> getConsumablesList();
    //获取设备列表
    List<Equipment> getEquipmentList();

}
