package com.codingfuture.web.controller;

import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.entity.Dept;
import com.codingfuture.service.DeptService;
import com.codingfuture.util.Result;
import com.codingfuture.util.ResultMsg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 部门controller
 *
 * @author wangchen
 */
@RestController
@RequestMapping("/v2")
public class DeptController {
    @Autowired
    private DeptService deptService;

    ResultMsg result = null;

    /**
     * 查询所有部门信息
     *
     * @param page,rows,dept
     * @return DataGridViewResult对象
     */
    @GetMapping("/dep/listByPage")
    public Result deptList(@RequestParam int page, @RequestParam int rows, Dept dept) {
        Page<Dept> objectPage = PageHelper.startPage(page, rows);
        deptService.findAllDept(dept);
        PageDataDTO<Dept> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }

    /**
     * 添加部门信息
     *
     * @param dept
     * @return
     */
    @PostMapping("/dep/add")
    public ResultMsg deptAdd(Dept dept) {
        if (deptService.addDept(dept) > 0) {
            result = ResultMsg.SUCCESS(200, "增加成功");
        } else if (deptService.addDept(dept) == -1) {
            result = ResultMsg.error(0, "部门名称与联系电话不能为空");
        } else if (deptService.addDept(dept) == -2) {
            result = ResultMsg.error(0, "部门名称必须为汉字");
        } else if (deptService.addDept(dept) == -3) {
            result = ResultMsg.error(0, "联系电话格式不正确");
        } else if (deptService.addDept(dept) == -4) {
            result = ResultMsg.error(0, "部门名称不能与已有内容重复");
        } else {
            result = ResultMsg.error(0, "增加失败");
        }
        return result;
    }

    /**
     * 回显信息
     *
     * @param id
     * @return
     */
    @GetMapping("/dep/selectone")
    public Result selectOne(Long id) {
        return Result.success(new PageDataDTO<Dept>(deptService.selectOneUpdateDept(id)));
    }

    /**
     * 更新部门信息
     *
     * @param dept
     * @return
     */
    @PostMapping("/dep/update")
    public ResultMsg updateDept(Dept dept) {
        if (deptService.updateDept(dept) > 0) {
            result = ResultMsg.SUCCESS(200, "修改成功");
        } else if (deptService.updateDept(dept) == -1) {
            result = ResultMsg.error(0, "部门名称与联系电话不能为空");
        } else if (deptService.updateDept(dept) == -2) {
            result = ResultMsg.error(0, "部门名称必须为汉字");
        } else if (deptService.updateDept(dept) == -3) {
            result = ResultMsg.error(0, "联系电话格式不正确");
        } else if (deptService.updateDept(dept) == -4) {
            result = ResultMsg.error(0, "部门名称不能与已有内容重复");
        } else {
            result = ResultMsg.error(0, "修改失败");
        }
        return result;
    }

    @DeleteMapping("/dep")
    public ResultMsg deleteDept(Long id) {
        if (deptService.deleteDept(id) > 0) {
            result = ResultMsg.SUCCESS(200, "删除成功");
        } else if (deptService.deleteDept(id) == -1) {
            result = ResultMsg.SUCCESS(0, "您删除的部门下有员工信息不能被删除");
        } else {
            result = ResultMsg.error(0, "删除失败");
        }
        return result;
    }
}
