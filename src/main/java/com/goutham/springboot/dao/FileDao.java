package com.goutham.springboot.dao;

import java.util.List;

import com.goutham.springboot.entity.File;

public interface FileDao {
	public void addFile(File file);
	public File getFileById(Long id);
	public List<File> getFile();
	public List<File> getFileByUserId(String userId);
	public List<File> getByCondition(File file);
}
