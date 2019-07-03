package com.propertyManagement.service.materialManagement;

import com.propertyManagement.mapper.materialManagement.MaterialMapper;
import com.propertyManagement.pojo.Consumables;
import com.propertyManagement.pojo.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    public List<Consumables> getConsumablesList(){
        return materialMapper.getConsumablesList();
    }

    public List<Equipment> getEquipment(){
        return materialMapper.getEquipmentList();
    }

}
