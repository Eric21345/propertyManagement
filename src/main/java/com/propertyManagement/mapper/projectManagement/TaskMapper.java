package com.propertyManagement.mapper.projectManagement;

import com.propertyManagement.pojo.Task;
import java.util.List;

public interface TaskMapper {

    List<Task> getTaskList();
    Task getTaskInfo(int id);
    void updateTaskInfo(int taskId, String name, String description, int planNum, int currentNum);
    void deleteTask(int taskId);
    void addTask(String name, String description, int planNum, int currentNum, int projectId);
}
