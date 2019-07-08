package com.propertyManagement.mapper.projectManagement;

import com.propertyManagement.pojo.SimpleTask;
import com.propertyManagement.pojo.Staff;
import com.propertyManagement.pojo.Task;
import com.propertyManagement.util.TaskParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {

    List<Task> getTaskList();
    Task getTaskInfo(int id);
    void updateTaskInfo(int taskId, String name, String description, int planNum);
    void deleteTask(int taskId);
    void addTask(String name, String description, int planNum, int currentNum, int projectId);
    List<Task> getTaskListByProjectId(int projectId);
    List<Integer> getTaskIdListByStaffId(int staffId);
    void deleteStaffFromTask(int staffId, int taskId);
    void addStaffIntoTask(int staffId, int taskId);
    void addTask(int projectId, @Param("taskList")TaskParam[] taskList);
    List<SimpleTask> getSimpleTaskListByProjectId(int projectId);
    Staff getMonitorByTaskId(int taskId);
}
