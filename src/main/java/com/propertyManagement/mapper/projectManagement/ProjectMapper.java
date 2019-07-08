package com.propertyManagement.mapper.projectManagement;

import com.propertyManagement.pojo.Contract;
import com.propertyManagement.pojo.Project;
import com.propertyManagement.pojo.Task;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface ProjectMapper {
        List<Project> getProjects();
        List<Project> getProjectsByStaffId(int staffId);
        List<Task> getTasksBySIdAndPId(int staffId, int projectId);
        void addProject(Project project);
        void addTask(String name, String description, int projectId, int staffId);
        void deleteTaskById(int taskId);
        void deleteContractById(int contractId);
        void deleteProjectById(int projectId);
        Project getProjectInfoById(int projectId);
        void updateProjectInfo(int id, String name, String description, int planNum, int currentNum, BigInteger planMoney, BigInteger currentMoney, String state);
        Contract getContractInfoById(int projectId);
        void updateContractInfo(int id, String name, Date startDate, Date endDate, String content, BigInteger totalMoney, int frequency, int receive, BigInteger receiveMoney, String remark);
        void deleteContract(int projectId);
//        Client getClientInfo(int clientId);
        void addContract(int projectId, String name, Date startDate, Date endDate, String content, BigInteger totalMoney, int frequency, int receive, BigInteger receiveMoney, String remark, int clientId);
}
