package com.goutham.springboot.service.imp;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goutham.springboot.dao.FileDao;
import com.goutham.springboot.dao.FileSave;
import com.goutham.springboot.dto.FileDto;
import com.goutham.springboot.entity.File;
import com.goutham.springboot.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileDao fileDao;
	@Autowired
	FileSave fileRepo;

	@Override
	@Transactional
	public void addFile(FileDto dto, MultipartFile file) {
		String fileId = fileRepo.saveFile(file);
		
		File file =  new File();
		file.setFileId(fileId);
		file.setFileName(file.getOriginalFilename());
		file.setOwner(dto.getOwner());
		file.setSize(file.getSize());
		file.setComment(dto.getComment());
		file.setUploadTime(new Date(System.currentTimeMillis()));
		
		fileDao.addFile(file);
	}

	@Override
	@Transactional
	public FileDto getFileById(Long id) {
		File temp = fileDao.getFileById(id);
		if ( temp == null) {
			return null;
		}
		return new FileDto(temp);
	}

	
	@Override
	@Transactional
	public List<FileDto> getFileMetadata() {
		List<File> metadatas = fileDao.getFile();
		List<FileDto> dtos = new ArrayList<FileDto>();
		for (File metadata : metadatas) {
			dtos.add(new FileDto(metadata));
		}
		//throw new RuntimeException("Method not supported");
		return dtos;
	}

	@Override
	public byte[] getFileByMetaId(Long id) {
		File metadata = fileDao.getFileById(id);
		if (metadata == null) {
			return null;
		}
		return fileRepo.getFile(metadata.getFileId());
	}
	
	@Override
	public List<FileDto> getFileByCondition(FileDto dto) {
		List<File> metadatas = fileDao.getByCondition(new File(dto));
		List<FileDto> dtos = new ArrayList<FileDto>();
		
		for (File metadata : metadatas) {
			dtos.add(new FileDto(metadata));
		}
		return dtos;
	}

}
