package com.goutham.springboot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.goutham.springboot.dto.FileDto;
import com.goutham.springboot.entity.File;

public interface FileService {
	public void addFile(FileDto dto, MultipartFile file);
	public FileDto getFileById(Long id);
	public List<FileDto> getFileByCondition(FileDto dto);
	public List<FileDto> getFileMetadata();
	public byte[] getFileByMetaId(Long id);
}