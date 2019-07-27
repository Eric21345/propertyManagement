package com.propertyManagement.controller.projectManagement;

import com.propertyManagement.pojo.SimpleTask;
import com.propertyManagement.pojo.Staff;
import com.propertyManagement.pojo.Task;
import com.propertyManagement.service.projectManagement.TaskService;
import com.propertyManagement.util.ProjectAndTaskParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    //获取项目对应的岗位列表
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getTaskListByProjectId")
    public Map getTaskListByProjectId(@RequestParam("projectId") int projectId){
        List<Task> taskList = taskService.getTaskListByProjectId(projectId);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("taskList", taskList);
        return map;
    }

    //获取岗位详情
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getTaskInfoById")
    public Map getTaskInfoById(@RequestParam("id") int id){
        Task task = taskService.getTaskInfoById(id);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("taskInfo", task);
        return map;
    }

    //更新岗位信息
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("updateTaskInfo")
    public Map updateTaskInfo(@RequestParam("id") int id,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("planNum") int planNum,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        taskService.updateTaskInfo(id, name, description, planNum, new Date(sdf.parse(startDate).getTime()),
                new Date(sdf.parse(endDate).getTime()));
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //获取公司所有的外勤员工列表
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getFieldStaffByCompanyId")
    public Map getFieldStaffByCompanyId(@RequestParam("companyId") int companyId){
        List<Staff> fieldStaffList = taskService.getFieldStaffByCompanyId(companyId);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("fieldStaffList", fieldStaffList);
        return map;
    }

    //依据岗位id获取班长
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getMonitorByTaskId")
    public Map getMonitorByTaskId(@RequestParam("taskId") int taskId){
        Map map = new HashMap();
        Staff monitor = taskService.getMonitorByTaskId(taskId);
        map.put("status", 1);
        map.put("monitor", monitor);
        return map;
    }

    //删除岗位
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("deleteTask")
    public Map deleteTask(@RequestParam("id") int id){
        taskService.deleteTask(id);
        //删除岗位的同时还需将与这个岗位相关的所有staff外键置空


        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //添加岗位
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("addTask")
    public Map addTask(@RequestParam("name") String name,
                       @RequestParam("description") String description,
                       @RequestParam("planNum") int planNun,
                       @RequestParam("currentNum") int currentNum,
                       @RequestParam("projectId") int projectId){
        taskService.addTask(name, description, planNun, currentNum, projectId);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //添加项目时，给项目添加岗位列表
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("addTaskList")
    public Map addTaskList(@RequestBody ProjectAndTaskParam projectAndTaskParam){
        taskService.addTaskList(projectAndTaskParam.getProjectId(), projectAndTaskParam.getTaskList());
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //获取某个项目的岗位列表以及以及每个岗位的班长名称
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getSimpleTaskListByProjectId")
    public Map getSimpleTaskListByProjectId(@RequestParam("projectId") int projectId){
        Map map = new HashMap();
        List<SimpleTask> simpleTaskList = taskService.getSimpleTaskListByProjectId(projectId);
        map.put("status", 1);
        map.put("simpleTaskList", simpleTaskList);
        return map;
    }

    //获取岗位id对应的员工列表(不包括班长)
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getStaffListByTaskId")
    public Map getStaffListByTaskId(@RequestParam("taskId") int taskId){
        Map map = new HashMap();
        List<Staff> staffList = taskService.getStaffListByTaskId(taskId);
        map.put("status", 1);
        map.put("staffList", staffList);
        return map;
    }


    //获取公司与某个岗位不相关的所有的外勤员工
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getFieldStaffListByCompanyIdAndTaskId")
    public Map getFieldStaffListByCompanyIdAndTaskId(@RequestParam("companyId") int companyId,
                                                     @RequestParam("taskId") int taskId){
        List<Staff> fieldStaffList = taskService.getFieldStaffListByCompanyIdAndTaskId(companyId, taskId);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("fieldStaffList", fieldStaffList);
        return map;
    }
}
