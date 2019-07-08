package com.propertyManagement.util;

public class ProjectAndTaskParam {
    private int projectId;
    private TaskParam[] taskList;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = Integer.parseInt(projectId);
    }

    public TaskParam[] getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskParam[] taskList) {
        this.taskList = taskList;
    }
}
