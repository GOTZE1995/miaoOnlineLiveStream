package com.miao.power.dao;

import java.util.List;

import com.miao.core.dao.BaseDao;
import com.miao.entity.Power;

/**
 * 权限数据访问层接口
 * 
 * @author lanyun 2016/11/16
 */
public interface PowerDao extends BaseDao<Power> {
	// 模糊查询
	public List<Power> findBysearchName(String searchName);
}
