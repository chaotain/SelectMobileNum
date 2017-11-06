package com.offcn.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.offcn.po.MobileNumber;
import com.offcn.service.MobileNumberService;

@Controller
public class MobileNumberController {

	@Resource
	MobileNumberService mobileNumberService;
	@RequestMapping("/selectnum")
	public String selectnum(Model model, int mobilenumber){
		System.out.println(mobilenumber);
		MobileNumber mobilenum = mobileNumberService.getnum(mobilenumber);
		System.out.println(mobilenum.getMobileAreal());
		model.addAttribute("mobilenum", mobilenum);
		return "success";
	}
}
