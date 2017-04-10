package com.goutham.springboot.exception;

import java.io.IOException;

public class FileUploadException extends RuntimeException {

	private static final long serialVersionUID = 5840102189210994923L;
	
	public FileUploadException(IOException ex) {
		super(ex);
	}
}
