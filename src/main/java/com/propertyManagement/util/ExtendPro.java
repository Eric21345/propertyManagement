package com.propertyManagement.util;

import com.propertyManagement.pojo.Project;
import com.propertyManagement.pojo.Task;

import java.util.List;

public class ExtendPro extends Project {
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
