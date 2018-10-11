package com.propertyManagement.controller;
import com.propertyManagement.pojo.TestStaff;
import com.propertyManagement.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("testPage")
    public ModelAndView listStaff(){
        ModelAndView modelAndView = new ModelAndView();

        List<TestStaff> list = testService.list();
        TestStaff[] staffs = new TestStaff[5];
        for(int i = 0; i < list.size(); i++){
            staffs[i] = list.get(i);
        }
        //modelAndView.addObject("staffList", list);
        modelAndView.addObject("staff", staffs[0].getName());
        modelAndView.setViewName("test");
        return modelAndView;
    }

}
