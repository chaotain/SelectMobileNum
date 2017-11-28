package com.offcn.service;

import javax.jws.WebService;

import com.offcn.po.MobileNumber;
@WebService
public interface MobileNumberService {

	public MobileNumber getnum(String mobilenumber);
}
