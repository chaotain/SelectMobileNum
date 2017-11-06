package com.offcn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.offcn.dao.MobileNumberDao;
import com.offcn.po.MobileNumber;
@Service
public class MobileNumberServiceImpl implements MobileNumberService{

	@Resource
	MobileNumberDao mobileNumberDao;
	public MobileNumber getnum(int mobilenumber) {
		// TODO Auto-generated method stub
		return mobileNumberDao.getnum(mobilenumber);
	}

}
