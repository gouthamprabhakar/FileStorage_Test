package com.goutham.springboot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.goutham.springboot.dto.FileDto;

//no POST method 

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateFileTest {
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void createFileMetadata() {
    	FileDto fileDto = new FileDto();
    	fileDto.setFileName("test.txt");
    	
        ResponseEntity<FileDto> responseEntity =
            restTemplate.postForEntity("/store_file/metadata", fileDto, FileDto.class);
        FileDto dto = responseEntity.getBody();
        
        System.err.println(responseEntity.getStatusCode());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        System.err.println(dto.getFileName());
        assertEquals("tst.txt", dto.getFileName());
    }
}
