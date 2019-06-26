package com.propertyManagement.service.projectManagement;

import com.propertyManagement.mapper.projectManagement.ProjectMapper;
import com.propertyManagement.pojo.Client;
import com.propertyManagement.pojo.Contract;
import com.propertyManagement.pojo.Project;
import com.propertyManagement.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> getProjects(){ return projectMapper.getProjects();}
    public List<Project> getProjectsByStaffId(int staffId){ return projectMapper.getProjectsByStaffId(staffId);}
    public List<Task> getTasksBySIdAndPId(int staffId, int projectId){ return projectMapper.getTasksBySIdAndPId(staffId, projectId) ;}
    public void addProject(String name, String description, int planNum, BigInteger planMoney){ projectMapper.addProject(name, description, planNum, planMoney);}
    public void addTask(String name, String description, int projectId, int staffId){ projectMapper.addTask(name, description, projectId, staffId);}
    public void deleteTaskById(int taskId){ projectMapper.deleteTaskById(taskId);}
    public void deleteContractById(int contractId){ projectMapper.deleteContractById(contractId);}
    public void deleteProjectById(int projectId){ projectMapper.deleteProjectById(projectId);}
    public Project getProjectInfoById(int projectId){ return projectMapper.getProjectInfoById(projectId);}
    public void updateProjectInfo(int id, String name, String description, int planNum, int currentNum, BigInteger planMoney, BigInteger currentMoney, String state){
        projectMapper.updateProjectInfo(id, name, description, planNum, currentNum, planMoney, currentMoney, state);
    };
    public Contract getContractInfoById(int projectId){ return projectMapper.getContractInfoById(projectId);}
    public void updateContractInfo(int id, String name, Date startDate, Date endDate, String content, BigInteger totalMoney, int frequency, int receive, BigInteger receiveMoney, String remark) {
        projectMapper.updateContractInfo(id, name, startDate, endDate, content, totalMoney, frequency, receive, receiveMoney, remark);
    }
    public void deleteContract(int projectId){ projectMapper.deleteContract(projectId);}
//    public Client getClientInfo(int clientId){ return projectMapper.getClientInfo(clientId); }
    public void addContract(int projectId, String name, Date startDate, Date endDate, String content, BigInteger totalMoney, int frequency, int receive, BigInteger receiveMoney, String remark, int clientId){
        projectMapper.addContract(projectId, name, startDate, endDate, content, totalMoney, frequency, receive, receiveMoney, remark, clientId);
    }
}
