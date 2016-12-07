package com.miao.power.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.miao.core.service.BaseServiceImpl;
import com.miao.entity.Power;
import com.miao.power.dao.PowerDao;

/**
 * 权限业务逻辑层实现
 * 
 * @author sunlanyun 2016/11/16
 */
@Service("powerService")
public class PowerServiceImpl extends BaseServiceImpl<Power> implements PowerService {

	@SuppressWarnings("unused")
	private PowerDao powerDao;

	@Resource
	public void setPowerDao(PowerDao powerDao) {
		super.setBaseDao(powerDao);
		this.powerDao = powerDao;
	}


}
