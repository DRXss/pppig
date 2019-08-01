package com.bawei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bawei.mapper.BankMapper;
import com.bawei.pojo.Bank;
import com.bawei.service.BankService;
@Service
public class BankServiceImpl implements BankService {
	@Autowired
	private BankMapper bd;

	public void insert(Bank b) {
		// TODO Auto-generated method stub
		bd.insert(b);
	}

	public Bank select(Long bid) {
		return bd.selectByPrimaryKey(bid);
	}

}
