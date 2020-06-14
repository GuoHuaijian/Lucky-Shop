package com.lucky.shop.admin.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.system.domain.SysTask;
import com.lucky.shop.admin.system.domain.SysTaskLog;
import com.lucky.shop.admin.system.service.SysTaskService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 定时任务
 *
 * @Author Guo Huaijian
 * @Date 2020/6/15 0:14
 */
@RestController
@RequestMapping("/task")
public class SysTaskController {

    @Autowired
    private SysTaskService taskService;

    /**
     * 获取定时任务管理列表
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.TASK})
    public ResponseResult list(String name) {
        List<SysTask> list = taskService.list(name);
        return ResponseResult.success(list);
    }

    /**
     * 新增定时任务管理
     *
     * @param task
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑定时任务", key = "name")
//    @RequiresPermissions(value = {Permission.TASK_EDIT})
    public ResponseResult add(@ModelAttribute @Valid SysTask task) {
        taskService.add(task);
        return ResponseResult.success();
    }

    /**
     * 删除定时任务管理
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    @BussinessLog(value = "删除定时任务", key = "id")
//    @RequiresPermissions(value = {Permission.TASK_DEL})
    public ResponseResult delete(@RequestParam Long id) {
        taskService.delete(id);
        return ResponseResult.success();
    }

    /**
     * 禁用定时任务
     *
     * @param taskId
     * @return
     */
    @PostMapping(value = "/disable")
    @BussinessLog(value = "禁用定时任务", key = "taskId")
//    @RequiresPermissions(value = {Permission.TASK_EDIT})
    public ResponseResult disable(@RequestParam Long taskId) {
        taskService.disable(taskId);
        return ResponseResult.success();
    }

    /**
     * 启用定时任务
     *
     * @param taskId
     * @return
     */
    @PostMapping(value = "/enable")
    @BussinessLog(value = "启用定时任务", key = "taskId")
//    @RequiresPermissions(value = {Permission.TASK_EDIT})
    public ResponseResult enable(@RequestParam Long taskId) {
        taskService.enable(taskId);
        return ResponseResult.success();
    }

    /**
     * 定时任务日志
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/logList")
//    @RequiresPermissions(value = {Permission.TASK})
    public ResponseResult logList(@RequestParam Long taskId) {
        Page<SysTaskLog> sysTaskLogPage = taskService.logList(taskId);
        return ResponseResult.success(sysTaskLogPage);
    }

}
