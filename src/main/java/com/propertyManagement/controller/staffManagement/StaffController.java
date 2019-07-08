package com.propertyManagement.controller.staffManagement;

import com.propertyManagement.pojo.Project;
import com.propertyManagement.pojo.Staff;
import com.propertyManagement.pojo.Task;
import com.propertyManagement.service.Login.AuthenticationService;
import com.propertyManagement.service.projectManagement.ProjectService;
import com.propertyManagement.service.projectManagement.TaskService;
import com.propertyManagement.service.staffManagement.StaffService;
import com.propertyManagement.util.TaskCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    //获取员工列表
    @SuppressWarnings("unchecked")
    @RequestMapping("getStaffList")
    @ResponseBody
    public Map getStaffList(){
        Map map = new HashMap();
        map.put("status",1);
        List<Staff> staffList = staffService.getStaffList();
        map.put("staffList",staffList);
        return map;

    }

    //依据员工id获得员工详情
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getStaffInfoById")
    public Map getStaffInfoById(@RequestParam("id") int id){
        Staff staffInfo = staffService.getStaffInfoById(id);
        Map map = new HashMap();
        map.put("status", 1);
        map.put("staffInfo", staffInfo);
        return map;
    }

    //删除员工
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("deleteStaff")
    public Map deleteStaff(@RequestParam("id") int id){
        staffService.deleteStaff(id);
        Map map = new HashMap();
        map.put("status",1);
        return map;
    }

    //管理人员处理员工认证申请
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("handleAuthentication")
    public Map addAuthentication(@RequestParam("authenticationId") int authenticationId,
                                 @RequestParam("handler") Staff handler){
        //需要对两张表进行操作
        //staff表更新type字段信息，authentication表更新处理信息
        //获取被处理员工的id并更新staff表
        staffService.updateStaffTypeById(authenticationService.getStaffIdByAuthenticationId(authenticationId));
        //更新authentication表
        authenticationService.updateAuthentication(handler.getId(), new Date(), 1, authenticationId);
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

    //获取公司所有员工
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getStaffListByCompanyId")
    public Map getStaffListByCompanyId(@RequestParam("companyId") int companyId){
        Map map = new HashMap();
        List<Staff> staffList = staffService.getStaffListByCompanyId(companyId);
        map.put("status", 1);
        map.put("staffList", staffList);
        return map;
    }

    //依据员工姓名模糊查询员工
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getStaffListByNameStr")
    public Map getStaffListByNameStr(@RequestParam("nameStr") String nameStr){
        Map map = new HashMap();
        List<Staff> staffList = staffService.getStaffListByNameStr(nameStr);
        map.put("status", 1);
        map.put("staffList", staffList);
        return map;
    }

    //获取公司所有项目列表，以及每个项目对应的岗位列表，以及员工相关联的岗位
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getMyTaskList")
    public Map getMyTaskList(@RequestParam("staffId") int staffId){
        List<Project> projectList = projectService.getProjects();
        List<List<Task>> taskList = new ArrayList<>();
        Map<Integer, List<Integer>> nowTaskListMap = new HashMap();
        for(int i = 0; i < projectList.size(); i++){
            List<Task> taskInList = taskService.getTaskListByProjectId(projectList.get(i).getId());
            if(taskInList.size() != 0) taskList.add(taskInList);
            for(int k = 0; k < taskInList.size(); k++){
                List<Integer> list = new ArrayList<>();
                list.add(i);list.add(k);
                nowTaskListMap.put(taskInList.get(k).getId(), list);
            }
        }
        //查询与当前员工相关的所有的taskId
        List<Integer> idList = taskService.getTaskIdListByStaffId(staffId);
        List<List<Integer>> nowTask = new ArrayList<>();
        for(Integer id:idList){
            nowTask.add(nowTaskListMap.get(id));
        }
        Map map = new HashMap();
        map.put("nowTask", nowTask);
        map.put("projectList", projectList);
        map.put("taskList", taskList);
        map.put("status", 1);
        return map;
    }

    //更新某个员工的岗位列表
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("updateStaffTaskList")
    public Map updateStaffTaskList(@RequestParam("staffId") int staffId,
                                @RequestParam("taskIdListBefore") int[] taskIdListBefore,
                                @RequestParam("taskIdListAfter") int[] taskIdListAfter){
        System.out.println(Arrays.toString(taskIdListBefore) + "\n" + Arrays.toString(taskIdListAfter));
        //找到两个数组相同的部分
        Arrays.sort(taskIdListBefore);
        Arrays.sort(taskIdListAfter);
        int lenBefore = taskIdListBefore.length, lenAfter = taskIdListAfter.length, i = 0, j = 0;
        while (i < lenBefore && j < lenAfter){
             if(taskIdListBefore[i] < taskIdListAfter[j]){
                //删除before岗位中的员工
                 taskService.deleteStaffFromTask(staffId, taskIdListBefore[i]);
                 System.out.println("删除" + taskIdListBefore[i]);
                i++;
            }
            else if(taskIdListBefore[i] == taskIdListAfter[j]){
                i++; j++;
            }
            else{
                //添加员工到after岗位中
                 taskService.addStaffIntoTask(staffId, taskIdListAfter[j]);
                 System.out.println("添加" + taskIdListAfter[j]);
                j++;
            }
        }
        if(i != lenBefore){
            for(int a = i; i < lenBefore; i++){
                //删除before岗位中的员工
                taskService.deleteStaffFromTask(staffId, taskIdListBefore[i]);
                System.out.println("删除" + taskIdListBefore[a]);
            }
        }
        if(j != lenAfter){
            for(int a = j; j < lenAfter; j++){
                //添加员工到after岗位中
                taskService.addStaffIntoTask(staffId, taskIdListAfter[j]);
                System.out.println("添加" + taskIdListAfter[a]);
            }
        }
        Map map = new HashMap();
        map.put("status", 1);
        return map;
    }

}
