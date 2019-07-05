package com.propertyManagement.mapper.projectManagement;

import com.propertyManagement.pojo.Task;
import java.util.List;

public interface TaskMapper {

    List<Task> getTaskList();
    Task getTaskInfo(int id);
    void updateTaskInfo(int taskId, String name, String description, int planNum, int currentNum);
    void deleteTask(int taskId);
    void addTask(String name, String description, int planNum, int currentNum, int projectId);
    List<Task> getTaskListByProjectId(int projectId);
    List<Integer> getTaskIdListByStaffId(int staffId);
    void deleteStaffFromTask(int staffId, int taskId);
    void addStaffIntoTask(int staffId, int taskId);
}
