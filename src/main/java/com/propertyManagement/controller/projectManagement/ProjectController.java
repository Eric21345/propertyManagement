package com.propertyManagement.controller.projectManagement;

import com.propertyManagement.pojo.Client;
import com.propertyManagement.pojo.Contract;
import com.propertyManagement.pojo.Project;
import com.propertyManagement.pojo.Task;
import com.propertyManagement.service.clientManagement.ClientService;
import com.propertyManagement.service.projectManagement.ProjectService;
import com.propertyManagement.util.ExtendPro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api("项目管理")
@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ClientService clientService;


    //较低层次的管理人员
    //管理人员查询公司所有的项目

    @ApiOperation("获取所有项目")
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getAllProjects")
    public Map getAllProjects(){
        Map map = new HashMap();
        List<Project> projects = projectService.getProjects();
        map.put("projects", projects);
        return map;
    }

    //管理人员查询自己相关的项目以及负责的任务
    @RequestMapping("getTasks")
    public Map getTasks(@RequestParam("staffId") int staffId){
        Map map = new HashMap();
        List<ExtendPro> extendPros = new ArrayList<>();
        List<Project> projects = projectService.getProjectsByStaffId(staffId);
        for(Project project:projects){
            ExtendPro extendPro = new ExtendPro();
            List<Task> tasks = projectService.getTasksBySIdAndPId(staffId, project.getId());
            extendPro.setTasks(tasks);
            extendPro.setId(project.getId());
            extendPro.setName(project.getName());
            extendPro.setDescription(project.getDescription());
            extendPro.setPlanNum(project.getPlanNum());
            extendPro.setCurrentNum(project.getCurrentNum());
            extendPro.setPlanMoney(project.getPlanMoney());
            extendPro.setCurrentMoney(project.getCurrentMoney());
            extendPro.setState(project.getState());
            extendPros.add(extendPro);
        }
        map.put("MyProjects", extendPros);
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
    public Map selectProject(@RequestParam(value = "name") String projectName){
        List<Project> projects = projectService.getProjects();
        List<Project> projects2 = new ArrayList<>();
        for (Project project:projects){
            if(project.getName().contains(projectName)){
                projects2.add(project);
            }
        }
        for(Project project:projects2){
            System.out.println(project.getName());
        }
        Map map = new HashMap();
        map.put("status", 1);
        if (!projectName.equals("")){
            map.put("projects", projects2);
        }
        else {
            map.put("projects", projects);
        }
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
                                 @RequestParam("currentNum") int currentNum,
                                 @RequestParam("planMoney") BigInteger planMoney,
                                 @RequestParam("currentMoney") BigInteger currentMoney,
                                 @RequestParam("index") int index){
        String state = "未开始";
        if(index == 1){
            state = "进行中";
        }
        else if(index == 2){
            state = "已完成";
        }
        else if(index == 3){
            state = "已中断";
        }
        projectService.updateProjectInfo(id, name, description, planNum, currentNum, planMoney, currentMoney, state);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //依据项目id获取合同信息
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getContractInfoById")
    public Map getContractInfoById(@RequestParam("id") int id){
        Map map = new HashMap();
        Contract contract = projectService.getContractInfoById(id);
        if(contract != null){
            map.put("status", 1);
            map.put("contract", contract);
        }
        else {
            map.put("status", 0);
        }
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
    public Map addContract(@RequestParam("projectId") int projectId,
                           @RequestParam("name") String name,
                           @RequestParam("startDate") String startDate,
                           @RequestParam("endDate") String endDate,
                           @RequestParam("content") String content,
                           @RequestParam("totalMoney") BigInteger totalMoney,
                           @RequestParam("frequency") int frequency,
                           @RequestParam("receive") int receive,
                           @RequestParam("receiveMoney") BigInteger receiveMoney,
                           @RequestParam("remark") String remark,
                           @RequestParam("clientName") String clientName,
                           @RequestParam("Company") String company,
                           @RequestParam("phone") String phone,
                           @RequestParam("address") String address) throws ParseException{
        //判断数据库中客户是否存在
        Client client = clientService.selectClient(clientName, company, phone, address);
        if(client == null){
            clientService.addClient(clientName, company, phone, address);
        }
        //获取客户id
        int id = clientService.selectClient(clientName, company, phone, address).getId();
        //字符串转日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        projectService.addContract(projectId, name, sdf.parse(startDate), sdf.parse(endDate), content, totalMoney, frequency, receive, receiveMoney, remark, id);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }


}
