package com.lucky.shop.admin.system.controller;

import com.lucky.shop.admin.system.domain.SysDept;
import com.lucky.shop.admin.system.domain.vo.DeptNode;
import com.lucky.shop.admin.system.service.SysDeptService;
import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.common.core.utils.BeanUtil;
import com.lucky.shop.common.log.annotation.BussinessLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 部门
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 17:50
 */
@RestController
@RequestMapping("system/dept")
public class SysDeptController {

    private Logger logger = LoggerFactory.getLogger(SysDeptController.class);

    @Autowired
    private SysDeptService deptService;

    /**
     * 获取所有节点
     *
     * @return
     */
    @GetMapping(value = "/list")
//    @RequiresPermissions(value = {Permission.DEPT})
    public ResponseResult list() {
        List<DeptNode> list = deptService.DeptList();
        return ResponseResult.success(list);
    }

    /**
     * 编辑部门
     *
     * @param dept
     * @return
     */
    @PostMapping()
    @BussinessLog(value = "编辑部门", key = "simplename")
//    @RequiresPermissions(value = {Permission.DEPT_EDIT})
    public ResponseResult save(@ModelAttribute @Valid SysDept dept) {
        if (BeanUtil.isOneEmpty(dept, dept.getSimplename())) {
            throw new RuntimeException();
        }
        deptService.saveDept(dept);
        return ResponseResult.success();
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除部门", key = "id")
//    @RequiresPermissions(value = {Permission.DEPT_DEL})
    public ResponseResult remove(@RequestParam Long id) {
        logger.info("id:{}", id);
        if (id == null) {
            throw new RuntimeException();
        }
        deptService.remove(id);
        return ResponseResult.success();
    }
}
