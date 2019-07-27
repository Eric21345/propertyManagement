package com.propertyManagement.controller.projectManagement;

import com.propertyManagement.pojo.Client;
import com.propertyManagement.pojo.Company;
import com.propertyManagement.pojo.Contract;
import com.propertyManagement.pojo.Project;
import com.propertyManagement.service.projectManagement.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api("项目管理")
@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    //添加项目
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("addProject")
    public Map addProject(@RequestParam("name") String name,
                          @RequestParam("description") String description,
                          @RequestParam("planNum") int planNum,
                          @RequestParam("planMoney") BigInteger planMoney,
                          @RequestParam("companyId") int companyId,
                          @RequestParam(value = "projectImg", required = false) MultipartFile multipartFile) throws IOException{
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setPlanNum(planNum);
        project.setPlanMoney(planMoney);
        project.setCompanyId(companyId);
        String imgPath = null;
        //本地使用的路径
        //String localPath = "E:\\images\\";
        //服务器上使用的路径
        String localPath = "C:\\App\\pmApp\\";
        String filename = null;
        if(!multipartFile.isEmpty()){
            //uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //文件类型
            String contentType = multipartFile.getContentType();
            //获得文件后缀名
            String suffixName= contentType.substring(contentType.indexOf("/")+1);
            //得到 文件名
            filename = uuid+"."+suffixName;
            System.out.println(filename);
            //保存文件
            multipartFile.transferTo(new File(localPath + filename));
            imgPath = "/images/" + filename;
        }
        else imgPath = "/images/default.jpg";
        project.setImgPath(imgPath);
        projectService.addProject(project);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("projectId", project.getId());
        return map;
    }


    //管理员根据自己的id查看自己的项目
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getMyProjects")
    public Map getMyProjects(@RequestParam("staffId") int staffId){
        Map map = new HashMap();
        List<Project> myProjects = new ArrayList<>();
        myProjects = projectService.getProjectsByStaffId(staffId);
        map.put("status",1);
        map.put("myProjects",myProjects);
        return map;
    }


    //删除项目
    @RequestMapping("deleteProject")
    public void deleteProject(@RequestParam("projectId") int projectId){
        projectService.deleteProjectById(projectId);
    }


    //依据项目名称查询项目
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("selectProjectByName")
    public Map selectProject(@RequestParam("name") String projectName,
                             @RequestParam("companyId") int comapnyId){
        List<Project> projects;
        if(projectName.equals("")){
            projects = projectService.getProjectListByCompanyId(comapnyId);
        }
        else {
            projects = projectService.selectProjectByName(projectName);
        }
        Map map = new HashMap();
        map.put("status", 1);
        map.put("projects",projects);
        return map;
    }

    //依据项目id获得项目详情
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getProjectInfoById")
    public Map getProjectInfoById(@RequestParam("id") int id){
        Project project = projectService.getProjectInfoById(id);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("project", project);
        return map;
    }

    //更新项目信息
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("updateProjectInfo")
    public Map updateProjectInfo(@RequestParam("id") int id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("planNum") int planNum,
                                 @RequestParam("planMoney") BigInteger planMoney,
                                 @RequestParam("index") int index){
        projectService.updateProjectInfo(id, name, description, planNum, planMoney, index + 1);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //更新项目图片
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("updateProjectImg")
    public Map updateProjectImg(@RequestParam("projectId") int projectId,
                                @RequestParam("projectImg") MultipartFile multipartFile) throws IOException{
        String filename = null;
        String imgPath = null;
        if(!multipartFile.isEmpty()){
            //先获取项目之前的图片路径
            String imgPathBefore = projectService.getImgPathByProjectId(projectId);
            //本地使用的路径
            //String localPath = "E:\\images\\";
            //服务器上使用的路径
            String localPath = "C:\\App\\pmApp\\";
            String fileNameBefore = imgPathBefore.substring(imgPathBefore.lastIndexOf("/") + 1);
            //删除原图片
            File file = new File(localPath + fileNameBefore);
            if(file.exists()) file.delete();
            //上传新图片，并在数据库中添加图片访问路径
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String contentType = multipartFile.getContentType();
            String suffixName= contentType.substring(contentType.indexOf("/")+1);
            //得到 文件名
            filename = uuid+"."+suffixName;
            System.out.println(filename);
            multipartFile.transferTo(new File(localPath + filename));
        }
        imgPath = "/images/" + filename;
        projectService.updateImgPathByProjectId(projectId, imgPath);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //依据项目id获取合同以及客户信息
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getContractAndClientByProjectId")
    public Map getContractAndClientByProjectId(@RequestParam("projectId") int projectId){
        Map map = new HashMap();
        Contract contract = projectService.getContractInfoByProjectId(projectId);
        if(contract != null){
            Client client = projectService.getClientByContractId(contract.getId());
            map.put("contract", contract);
            map.put("client", client);
        }
        else {
            map.put("contract", null);
        }
        map.put("status", 1);
        return map;
    }

    //更新合同信息
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("updateContractInfo")
    public Map updateContractInfo(@RequestParam("id") int id,
                                  @RequestParam("name") String name,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam("content") String content,
                                  @RequestParam("totalMoney") BigInteger totalMoney,
                                  @RequestParam("frequency") int frequency,
                                  @RequestParam("receive") int receive,
                                  @RequestParam("receiveMoney") BigInteger receiveMoney,
                                  @RequestParam("remark") String remark) throws ParseException {
        Map map = new HashMap();
        //字符串转日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.parse(startDate));
        System.out.println(sdf.parse(endDate));
        projectService.updateContractInfo(id, name, sdf.parse(startDate), sdf.parse(endDate), content, totalMoney, frequency, receive, receiveMoney, remark);
        map.put("status", 1);
        return map;
    }

    //依据项目id删除合同
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("deleteContract")
    public Map deleteContract(@RequestParam("id") int id){
    projectService.deleteContract(id);
    Map map = new HashMap();
    map.put("status", 1);
    return map;
    }

    //添加合同
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("addContract")
    public Map addContract(@RequestParam("name") String name,
                           @RequestParam("startDate") String startDate,
                           @RequestParam("endDate") String endDate,
                           @RequestParam("workContent") String workContent,
                           @RequestParam("totalMoney") BigInteger totalMoney,
                           @RequestParam("frequency") int frequency,
                           @RequestParam("remark") String remark,
                           @RequestParam("clientId") int clientId,
                           @RequestParam("projectId") int projectId,
                           @RequestParam("companyId") int companyId) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Contract contract = new Contract();
        contract.setName(name);
        contract.setStartDate(new Date(sdf.parse(startDate).getTime()));
        contract.setEndDate(new Date(sdf.parse(endDate).getTime()));
        contract.setWorkContent(workContent);
        contract.setTotalMoney(totalMoney);
        contract.setFrequency(frequency);
        contract.setRemark(remark);
        contract.setClientId(clientId);
        contract.setProjectId(projectId);
        contract.setCompanyId(companyId);
        projectService.addContract(contract);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //获取公司对应所有项目(这里开始使用的是新表)
    @SuppressWarnings("unchecked")
    @RequestMapping("getProjectListByCompanyId")
    @ResponseBody
    public Map getProjectListByCompanyId(@RequestParam("companyId") int companyId){
        Map map = new HashMap();
        List<Project> projectList = projectService.getProjectListByCompanyId(companyId);
        map.put("status", 1);
        map.put("projectList", projectList);
        return map;
    }

    //员工id获取对应公司
    @SuppressWarnings("unchecked")
    @RequestMapping("getCompanyByStaffId")
    @ResponseBody
    public Map getCompanyByStaffId(@RequestParam("staffId") int staffId){
        Map map = new HashMap();
        Company company = projectService.getCompanyByStaffId(staffId);
        map.put("status", 1);
        map.put("company", company);
        return map;
    }

    //获取公司客户列表
    @SuppressWarnings("unchecked")
    @RequestMapping("getClientListByCompanyId")
    @ResponseBody
    public Map getClientListByCompanyId(@RequestParam("companyId") int companyId){
        List<Client> clientList = projectService.getClientListByCompanyId(companyId);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("clientList", clientList);
        return map;
    }

    //公司添加客户
    @SuppressWarnings("unchecked")
    @RequestMapping("addClient")
    @ResponseBody
    public Map addClient(@RequestParam("companyId") int companyId,
                         @RequestParam("name") String name,
                         @RequestParam("phone") String phone,
                         @RequestParam("address") String address,
                         @RequestParam("company") String company){
        Client client = new Client();
        client.setName(name);
        client.setPhone(phone);
        client.setAddress(address);
        client.setCompany(company);
        client.setCompanyId(companyId);
        projectService.addClient(client);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

}
