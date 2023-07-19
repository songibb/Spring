package com.yedam.java.book.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RentVO {

	private int rentNo;
	private int bookNo;
	private Integer rentPrice;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rentDate;
	private String rentStatus;
	
	private int no;
	private String name;
	private int sum;
	private int count;
	
}
