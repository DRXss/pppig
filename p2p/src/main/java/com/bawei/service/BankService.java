package com.bawei.service;

import com.bawei.pojo.Bank;

public interface BankService {
	void insert(Bank b);

	Bank select(Long bid);
}
