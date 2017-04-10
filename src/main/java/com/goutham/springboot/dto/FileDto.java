package com.goutham.springboot.dto;

import java.io.Serializable;

import com.goutham.springboot.entity.File;

@Data
public class FileDto implements Serializable{

	private static final long serialVersionUID = -4351281772747041537L;

	private int id;
	private String name;
	private String admin;
	private Long range;
	private String uploadTime;
	private String comment;
	
}
