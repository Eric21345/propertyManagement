package com.propertyManagement.mapper.projectManagement;

import com.propertyManagement.pojo.SimpleTask;
import com.propertyManagement.pojo.Staff;
import com.propertyManagement.pojo.Task;
import com.propertyManagement.util.TaskParam;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface TaskMapper {

    List<Task> getTaskList();
    Task getTaskInfoById(int id);
    void updateTaskInfo(int taskId, String name, String description, int planNum, Date startDate, Date endDate);
    void deleteTask(int taskId);
    void addTask(String name, String description, int planNum, int currentNum, int projectId);
    List<Task> getTaskListByProjectId(int projectId);
    List<Integer> getTaskIdListByStaffId(int staffId);
    void deleteStaffFromTask(int staffId, int taskId);
    void addStaffIntoTask(int staffId, int taskId);
    void addTaskList(int projectId, @Param("taskList")TaskParam[] taskList);
    List<SimpleTask> getSimpleTaskListByProjectId(int projectId);
    Staff getMonitorByTaskId(int taskId);
    List<Staff> getStaffListByTaskId(int taskId);
    List<Staff> getFieldStaffByCompanyId(int companyId);
    List<Staff> getFieldStaffListByCompanyIdAndTaskId(int companyId, int taskId);
}
