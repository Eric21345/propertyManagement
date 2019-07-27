package com.propertyManagement.service.projectManagement;

import com.propertyManagement.mapper.projectManagement.ProjectMapper;
import com.propertyManagement.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> getProjectsByStaffId(int staffId){ return projectMapper.getProjectsByStaffId(staffId);}
    public List<Task> getTasksBySIdAndPId(int staffId, int projectId){ return projectMapper.getTasksBySIdAndPId(staffId, projectId) ;}
    public void addProject(Project project){ projectMapper.addProject(project);}
    public void addTask(String name, String description, int projectId, int staffId){ projectMapper.addTask(name, description, projectId, staffId);}
    public void deleteTaskById(int taskId){ projectMapper.deleteTaskById(taskId);}
    public void deleteContractById(int contractId){ projectMapper.deleteContractById(contractId);}
    public void deleteProjectById(int projectId){ projectMapper.deleteProjectById(projectId);}
    public Project getProjectInfoById(int projectId){ return projectMapper.getProjectInfoById(projectId);}
    public void updateProjectInfo(int id, String name, String description, int planNum, BigInteger planMoney, int stateId){
        projectMapper.updateProjectInfo(id, name, description, planNum, planMoney, stateId);
    };
    public Contract getContractInfoByProjectId(int projectId){ return projectMapper.getContractInfoByProjectId(projectId);}
    public void updateContractInfo(int id, String name, Date startDate, Date endDate, String content, BigInteger totalMoney, int frequency, int receive, BigInteger receiveMoney, String remark) {
        projectMapper.updateContractInfo(id, name, startDate, endDate, content, totalMoney, frequency, receive, receiveMoney, remark);
    }
    public void deleteContract(int projectId){ projectMapper.deleteContract(projectId);}
    public void addContract(Contract contract){
        projectMapper.addContract(contract);
    }
    public List<Project> getProjectListByCompanyId(int companyId){
        return projectMapper.getProjectListByCompanyId(companyId);
    }
    public Company getCompanyByStaffId(int staffId){
        return projectMapper.getCompanyByStaffId(staffId);
    }
    public String getImgPathByProjectId(int projectId){
        return projectMapper.getImgPathByProjectId(projectId);
    }
    public void updateImgPathByProjectId(int projectId, String imgPath){
        projectMapper.updateImgPathByProjectId(projectId, imgPath);
    }
    public List<Project> selectProjectByName(String name){
        return projectMapper.selectProjectByName(name);
    }
    public Client getClientByContractId(int contractId){
        return projectMapper.getClientByContractId(contractId);
    }
    public List<Project> getSimpleProjectListByCompanyId(int companyId){
        return projectMapper.getSimpleProjectListByCompanyId(companyId);
    }
    public List<Client> getClientListByCompanyId(int companyId){
        return projectMapper.getClientListByCompanyId(companyId);
    }
    public void addClient(Client client){
        projectMapper.addClient(client);
    }
}
