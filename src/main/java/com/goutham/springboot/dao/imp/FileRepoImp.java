package com.goutham.springboot.dao.imp;

import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.goutham.springboot.dao.FileSave;

@Component
public class FileRepoImp implements FileSave {
	
	private static String storageDir = "file_repository/";
	private static final Logger LOG = LoggerFactory.getLogger(FileRepoImp.class);

	@Override
	public String saveFile(MultipartFile file) {
		String fileName = null;
		File newFile 	= null;
		
		try {
			fileName = new BigInteger(1, MessageDigest.getInstance("MD5")
					.digest(file.getBytes())).toString(16);
			newFile = new File(storageDir + fileName);
		} catch (IOException ex) {
			LOG.info("Fail to read " + file.getOriginalFilename());
			LOG.debug(ex.getMessage());
			ex.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException ex) {
			LOG.info("Fail to get MD5 fro " + file.getOriginalFilename());
			LOG.debug(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
		//the same file must have the same MD5
		if (newFile.exists()) {
			return fileName;
		}
		
		try (BufferedOutputStream outFile = new BufferedOutputStream(
				new FileOutputStream(newFile));) {
			outFile.write(file.getBytes());
		} catch (IOException ex) {
			LOG.info("Fail to save " + file.getOriginalFilename() + " on disk.");
			LOG.debug(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
		return fileName;
	}

	@Override
	public byte[] getFile(String fileName) {
		byte[] context = null;
		
		try (BufferedInputStream inFile = new BufferedInputStream(
				new FileInputStream(storageDir + fileName));) {
			context = new byte[inFile.available()];
			inFile.read(context);
		} catch (FileNotFoundException ex) {
			LOG.info(fileName + " not found");
			LOG.debug(ex.getMessage());
			ex.printStackTrace();
			return null;
		} catch (IOException ex) {
			LOG.info("Fail to read " + fileName);
			LOG.debug(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
				
		return context;
	}

}
