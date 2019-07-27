package com.propertyManagement.mapper.projectManagement;

import com.propertyManagement.pojo.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface ProjectMapper {
        List<Project> getProjectsByStaffId(int staffId);
        List<Task> getTasksBySIdAndPId(int staffId, int projectId);
        void addProject(Project project);
        void addTask(String name, String description, int projectId, int staffId);
        void deleteTaskById(int taskId);
        void deleteContractById(int contractId);
        void deleteProjectById(int projectId);
        Project getProjectInfoById(int projectId);
        void updateProjectInfo(int id, String name, String description, int planNum, BigInteger planMoney, int stateId);
        Contract getContractInfoByProjectId(int projectId);
        void updateContractInfo(int id, String name, Date startDate, Date endDate, String content, BigInteger totalMoney, int frequency, int receive, BigInteger receiveMoney, String remark);
        void deleteContract(int projectId);
        void addContract(Contract contract);
        List<Project> getProjectListByCompanyId(int companyId);
        Company getCompanyByStaffId(int staffId);
        String getImgPathByProjectId(int projectId);
        void updateImgPathByProjectId(int projectId, String imgPath);
        List<Project> selectProjectByName(String name);
        Client getClientByContractId(int contractId);
        List<Project> getSimpleProjectListByCompanyId(int companyId);
        List<Client> getClientListByCompanyId(int companyId);
        void addClient(Client client);
}
