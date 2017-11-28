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
	public String selectnum(Model model, String mobilenumber){
		MobileNumber mobilenum = mobileNumberService.getnum(mobilenumber);
		if(mobilenum!=null){
			model.addAttribute("msg", mobilenum.getMobileType()+"---"+mobilenum.getMobileAreal());
		}else{
			model.addAttribute("msg", "该号段暂时没有");
		}
		model.addAttribute("mobilenum", mobilenum);
		return "success";
	}
}
