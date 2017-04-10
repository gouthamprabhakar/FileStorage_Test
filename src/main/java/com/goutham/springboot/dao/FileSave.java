package com.goutham.springboot.dao;

import org.springframework.web.multipart.MultipartFile;

public interface FileSave {
	public String saveFile(MultipartFile file);
	public byte[] getFile(String name);
}
