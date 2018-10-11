package com.propertyManagement.service;

import com.propertyManagement.mapper.TestMapper;
import com.propertyManagement.pojo.TestStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;

    public List<TestStaff> list(){ return testMapper.list();}

}
