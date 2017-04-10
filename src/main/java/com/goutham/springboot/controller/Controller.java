package com.goutham.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goutham.springboot.dto.FileDto;
import com.goutham.springboot.service.FileService;

@RestController
@RequestMapping("/store")
public class Controller {

	@Autowired
	FileService fileService;
	
	@RequestMapping(value = "/metadata/{id}", method = RequestMethod.GET)
	public ResponseEntity<FileDto> getMetadata(
			@PathVariable(value="id") Long id) {
		FileDto fileDto = fileService.getFileMetadataById(id);
		if (fileDto == null) {
			return new ResponseEntity<FileDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FileDto>(fileDto, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public ResponseEntity<List<FileDto>> getFileMetadataByCondition(
			FileDto fileDto) {
		List<FileDto> dtos = fileService.getFileMetadataByCondition(fileDto);

		return new ResponseEntity<List<FileDto>>(dtos, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public ResponseEntity<FileDto> uploadFile(FileDto fileDto, MultipartFile file) {
		if (file.isEmpty()) {
			return new ResponseEntity<FileDto>(HttpStatus.NOT_FOUND);
		}
		fileService.addFileMetadata(fileDto, file);
		return new ResponseEntity<FileDto>(HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadFileById(@PathVariable(value="id") Long id) {
		FileDto fileDto = fileService.getFileMetadataById(id);
		byte[] file = fileService.getFileById(id);
		
		if (file == null || fileDto == null) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/octet-stream");
		headers.add("Content-Disposition", "attachment; filename = '" + fileDto.getName() + "'");
		
		return new ResponseEntity<byte[]>(file, headers, HttpStatus.FOUND);
	}
}
