package com.offcn.service;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.offcn.dao.MobileNumberDao;
import com.offcn.po.MobileNumber;
@Service
@WebService
public class MobileNumberServiceImpl implements MobileNumberService{

	@Resource
	MobileNumberDao mobileNumberDao;
	public MobileNumber getnum(String mobilenumber) {
		String num = mobilenumber.substring(0, 7);
		return mobileNumberDao.getnum(num);
	}

}
