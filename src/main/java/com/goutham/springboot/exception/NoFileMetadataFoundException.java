package com.goutham.springboot.exception;

public class NoFileMetadataFoundException extends RuntimeException {

	private static final long serialVersionUID = 7777286916196792196L;

	public NoFileMetadataFoundException(Long id) {
		super("File " + id + " metadata Not Found");
	}
}
