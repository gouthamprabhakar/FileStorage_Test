package com.goutham.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.goutham.springboot.dto.FileDto;

@Data
@Entity
@Table(name="file")
public class File implements Serializable {
 
	private static final long serialVersionUID = -6396430720455092004L;

	private Long id;
	private String name;
	private String admin;
	private Long range;
	private Date uploadTime;
	private String comment;
	private String fileId;
	
 
}
