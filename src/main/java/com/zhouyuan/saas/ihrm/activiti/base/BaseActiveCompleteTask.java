package com.zhouyuan.saas.ihrm.activiti.base;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class BaseActiveCompleteTask {

    public static void main(String[] args) {
        completeTask("2505");
    }

    /**
     *  背后操作的表：
     *  act_hi_actinst
     *  act_hi_identitylink
     *  act_hi_taskinst
     *  act_ru_identitylink
     *  act_ru_task
     *  `act_ru_execution`
     * @param taskId 任务ID
     */
    public static void completeTask(String taskId){

        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        //3.处理任务,结合当前用户任务列表的查询操作的话,例如任务ID:5002
        taskService.complete(taskId);
    }

    /**
     * 根据流程定义主键和任务执行人完成任务
     * @param definitionKey
     * @param assignee
     */
    public static void completeTaskByAssignee(String definitionKey,String assignee){
        Task task = BaseActivitiQueryTask.queryTask(definitionKey,assignee).get(0);
        if (null != task){
            BaseActiveCompleteTask.completeTask(task.getId());
            System.out.println(task.getId() + "任务执行完毕");
        }
    }
}
