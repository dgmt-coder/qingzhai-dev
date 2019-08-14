package com.qingzhai.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.qingzhai.user.pojo.Department;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface DepartmentDao extends JpaRepository<Department,String>,JpaSpecificationExecutor<Department>{
	
}
