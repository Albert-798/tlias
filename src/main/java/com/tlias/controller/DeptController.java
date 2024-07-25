package com.tlias.controller;

import com.tlias.pojo.Dept;
import com.tlias.pojo.Result;
import com.tlias.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 查询所有部门数据
    @GetMapping
    public Result getAllDepts() {
        log.info("查询所有部门数据");
        try {
            List<Dept> deptList = deptService.list();
            return Result.success(deptList);
        } catch (Exception e) {
            log.error("查询所有部门数据失败", e);
            return Result.error("查询所有部门数据失败");
        }
    }

    // 根据ID删除部门
    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable Integer id) {
        log.info("根据ID删除部门：{}", id);
        try {
            deptService.delete(id);
            return Result.success();
        } catch (Exception e) {
            log.error("根据ID删除部门失败：{}", id, e);
            return Result.error("删除部门失败");
        }
    }

    // 新增部门
    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        log.info("新增部门：{}", dept);
        try {
            deptService.add(dept);
            return Result.success();
        } catch (Exception e) {
            log.error("新增部门失败：{}", dept, e);
            return Result.error("新增部门失败");
        }
    }
}
