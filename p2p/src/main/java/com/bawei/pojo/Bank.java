package com.bawei.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_bank")
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long bid;
	String bname;
	String identity;
	String idCard;
	String bankid;
	String pwd;
	String phone;
}
