package com.bawei.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bawei.pojo.Bank;
import com.bawei.service.BankService;
import com.bawei.util.SmsUtil;
@RequestMapping("bank")
@Controller
public class BankController {
	@Autowired
	private BankService bankService;

	@RequestMapping("bankinsert")
	public String bankinsert(String uname, String registrant, String pipelineNumber, String phone, HttpServletRequest request) {
		request.getSession().setAttribute("phone",phone);
		request.getSession().setAttribute("number",pipelineNumber);
		String hou=uname.substring(0,1);
		hou=hou+"**";
		request.getSession().setAttribute("uname",uname);
		request.getSession().setAttribute("hou",hou);
		String arr="";
		if(registrant=="1"){
			arr="出借人";
		}else{
			arr="借款人";
		}
		request.getSession().setAttribute("arr",arr);
		return "bank";
	}

	@RequestMapping("insert")
	public String insert(Bank b,String state,int code,HttpServletRequest request) {
		int  code1 = (Integer) request.getSession().getAttribute("code");
		if (state.equals("1")){
			if (code==code1){
				Date date=new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				String format = sdf.format(date);
				Random random=new Random();
				int min=100000;
				int max=999999;
				int code2 = random.nextInt(max);
				if(code2<min) {
					code2=code2+min;
				}
				String bankid=format+""+code2;
				System.out.println(bankid);
				b.setBankid(bankid);
				bankService.insert(b);
				System.out.println(b.getBid());
				request.getSession().setAttribute("bid",b.getBid());
				return "redirect:list";
			}
		}
		return "http://10.1.65.91:8080/toopenaccount";
	}

	@RequestMapping("list")
	public String list(HttpServletRequest request){
		Long bid = (Long) request.getSession().getAttribute("bid");
		Bank select = bankService.select(bid);
		request.setAttribute("sl",select);
		return "list";
	}
	
	@RequestMapping("yanz")
	@ResponseBody
	public String yanz(String phone,String idCard,String pwd,String identity,String uname, HttpServletRequest request,String card,String cards) {
		Random r=new Random();
		int min=100000;
		int max=999999;
		int code = r.nextInt(max);
		if(code<min) {
			code=code+min;
		}
		request.getSession().setAttribute("code", code);
		request.getSession().setAttribute("idCard",idCard);
		request.getSession().setAttribute("pwd",pwd);
		request.getSession().setAttribute("identity",identity);
		request.getSession().setAttribute("uname",uname);
		request.getSession().setAttribute("number",card);
		request.getSession().setAttribute("arr",cards);
		SmsUtil.sendSms(phone, code);
		System.out.println(phone+"验证码"+code);
		return phone;
	}
}
