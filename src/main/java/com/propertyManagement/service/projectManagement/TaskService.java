package com.propertyManagement.service.projectManagement;

import com.propertyManagement.mapper.projectManagement.TaskMapper;
import com.propertyManagement.pojo.SimpleTask;
import com.propertyManagement.pojo.Staff;
import com.propertyManagement.pojo.Task;
import com.propertyManagement.util.TaskParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;

    public List<Task> getTaskList(){
        return taskMapper.getTaskList();
    }

    public Task getTaskInfoById(int id){
        return taskMapper.getTaskInfoById(id);
    }

    public void deleteTask(int id){
        taskMapper.deleteTask(id);
    }

    public void updateTaskInfo(int taskId, String name, String description, int planNum, Date startDate, Date endDate){
        taskMapper.updateTaskInfo(taskId, name, description, planNum, startDate, endDate);
    }

    public void addTask(String name, String description, int planNum, int currentNum, int projectId){
        taskMapper.addTask(name, description, planNum, currentNum, projectId);
    }

    public List<Task> getTaskListByProjectId(int projectId){
        return taskMapper.getTaskListByProjectId(projectId);
    }

    public List<Integer> getTaskIdListByStaffId(int staffId){
        return taskMapper.getTaskIdListByStaffId(staffId);
    }

    public void deleteStaffFromTask(int staffId, int taskId){
        taskMapper.deleteStaffFromTask(staffId, taskId);
    }

    public void addStaffIntoTask(int staffId, int taskId){
        taskMapper.addStaffIntoTask(staffId, taskId);
    }

    public void addTaskList(int projectId, TaskParam[] taskList){
        taskMapper.addTaskList(projectId, taskList);
    }

    public List<SimpleTask> getSimpleTaskListByProjectId(int projectId){
        return taskMapper.getSimpleTaskListByProjectId(projectId);
    }

    public Staff getMonitorByTaskId(int taskId){
        return taskMapper.getMonitorByTaskId(taskId);
    }

    public List<Staff> getStaffListByTaskId(int taskId){
        return taskMapper.getStaffListByTaskId(taskId);
    }

    public List<Staff> getFieldStaffByCompanyId(int companyId){
        return taskMapper.getFieldStaffByCompanyId(companyId);
    }
    public List<Staff> getFieldStaffListByCompanyIdAndTaskId(int companyId, int taskId){
        return taskMapper.getFieldStaffListByCompanyIdAndTaskId(companyId, taskId);
    }
}
