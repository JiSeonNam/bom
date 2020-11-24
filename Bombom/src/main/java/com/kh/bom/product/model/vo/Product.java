package com.kh.bom.product.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	private String pdtNo;
	private String pdtCategory;
	private int pdtPrice;
	private String pdtName;
	private String pdtIntro;
	private String pdtStatus;
	private Date pdtDate;
	private String pdtDetailImage;
	private String eventNoRef;
}