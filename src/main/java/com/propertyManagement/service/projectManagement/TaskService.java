package com.propertyManagement.service.projectManagement;

import com.propertyManagement.mapper.projectManagement.TaskMapper;
import com.propertyManagement.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;

    public List<Task> getTaskList(){
        return taskMapper.getTaskList();
    }

    public Task getTaskInfo(int id){
        return taskMapper.getTaskInfo(id);
    }

    public void deleteTask(int id){
        taskMapper.deleteTask(id);
    }

    public void updateTaskInfo(int taskId, String name, String description, int planNum, int currentNum){
        taskMapper.updateTaskInfo(taskId, name, description, planNum, currentNum);
    }

    public void addTask(String name, String description, int planNum, int currentNum, int projectId){
        taskMapper.addTask(name, description, planNum, currentNum, projectId);
    }
}
