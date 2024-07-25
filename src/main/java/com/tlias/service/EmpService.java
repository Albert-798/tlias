package com.tlias.service;

import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

//员工业务规则
public interface EmpService {
    /**
     * 条件分页查询
     * @param page 页码
     * @param pageSize 每页展示记录数
     * @return
     */

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);
    /**
     * 批量删除操作
     * @param ids id集合
     */
    void delete(List<Integer> ids);

    /**
     * 保存员工信息
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    public Emp getById(Integer id);

    /**
     * 更新员工
     * @param emp
     */
    public void update(Emp emp);

    /**
     * 用户登录
     * @param emp
     * @return
     */
    public Emp login(Emp emp);

}