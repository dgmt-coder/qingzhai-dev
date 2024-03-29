package com.qingzhai.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import com.qingzhai.user.dao.UserDao;
import com.qingzhai.user.pojo.User;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findSearch(Map whereMap, int page, int size) {
		Specification<User> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return userDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		return userDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param user
	 */
	public void add(User user) {
		user.setId( idWorker.nextId()+"" );
		userDao.save(user);
	}

	/**
	 * 修改
	 * @param user
	 */
	public void update(User user) {
		userDao.save(user);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		userDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 主键
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 学号/教师编号
                if (searchMap.get("schId")!=null && !"".equals(searchMap.get("schId"))) {
                	predicateList.add(cb.like(root.get("schId").as(String.class), "%"+(String)searchMap.get("schId")+"%"));
                }
                // 用户名
                if (searchMap.get("username")!=null && !"".equals(searchMap.get("username"))) {
                	predicateList.add(cb.like(root.get("username").as(String.class), "%"+(String)searchMap.get("username")+"%"));
                }
                // 密码
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // 个性签名
                if (searchMap.get("sign")!=null && !"".equals(searchMap.get("sign"))) {
                	predicateList.add(cb.like(root.get("sign").as(String.class), "%"+(String)searchMap.get("sign")+"%"));
                }
                // 昵称，如果不输入为username
                if (searchMap.get("nikename")!=null && !"".equals(searchMap.get("nikename"))) {
                	predicateList.add(cb.like(root.get("nikename").as(String.class), "%"+(String)searchMap.get("nikename")+"%"));
                }
                // 院系ID
                if (searchMap.get("departmentId")!=null && !"".equals(searchMap.get("departmentId"))) {
                	predicateList.add(cb.like(root.get("departmentId").as(String.class), "%"+(String)searchMap.get("departmentId")+"%"));
                }
                // 年级专业
                if (searchMap.get("gread")!=null && !"".equals(searchMap.get("gread"))) {
                	predicateList.add(cb.like(root.get("gread").as(String.class), "%"+(String)searchMap.get("gread")+"%"));
                }
                // 主修方向的名称
                if (searchMap.get("major")!=null && !"".equals(searchMap.get("major"))) {
                	predicateList.add(cb.like(root.get("major").as(String.class), "%"+(String)searchMap.get("major")+"%"));
                }
                // 主修方向的Id
                if (searchMap.get("majorId")!=null && !"".equals(searchMap.get("majorId"))) {
                	predicateList.add(cb.like(root.get("majorId").as(String.class), "%"+(String)searchMap.get("majorId")+"%"));
                }
                // 头像
                if (searchMap.get("avatar")!=null && !"".equals(searchMap.get("avatar"))) {
                	predicateList.add(cb.like(root.get("avatar").as(String.class), "%"+(String)searchMap.get("avatar")+"%"));
                }
                // 性别（1男 0女）
                if (searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))) {
                	predicateList.add(cb.like(root.get("sex").as(String.class), "%"+(String)searchMap.get("sex")+"%"));
                }
                // 手机号
                if (searchMap.get("mobile")!=null && !"".equals(searchMap.get("mobile"))) {
                	predicateList.add(cb.like(root.get("mobile").as(String.class), "%"+(String)searchMap.get("mobile")+"%"));
                }
                // 邮箱地址
                if (searchMap.get("email")!=null && !"".equals(searchMap.get("email"))) {
                	predicateList.add(cb.like(root.get("email").as(String.class), "%"+(String)searchMap.get("email")+"%"));
                }
                // 宿舍号
                if (searchMap.get("roomId")!=null && !"".equals(searchMap.get("roomId"))) {
                	predicateList.add(cb.like(root.get("roomId").as(String.class), "%"+(String)searchMap.get("roomId")+"%"));
                }
                // 是否关闭（1关闭 0开启）
                if (searchMap.get("isClose")!=null && !"".equals(searchMap.get("isClose"))) {
                	predicateList.add(cb.like(root.get("isClose").as(String.class), "%"+(String)searchMap.get("isClose")+"%"));
                }
                // 角色名称
                if (searchMap.get("role")!=null && !"".equals(searchMap.get("role"))) {
                	predicateList.add(cb.like(root.get("role").as(String.class), "%"+(String)searchMap.get("role")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
