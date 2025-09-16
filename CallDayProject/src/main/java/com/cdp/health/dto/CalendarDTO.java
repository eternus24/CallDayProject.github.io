package com.cdp.health.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CalendarDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer calNum;
	
	@Column
	private String calSubject;

	@Column
	private String calContent;
	
	@Column
	private String calPart;
	
	@Column
	private String calName;
	
	@Column
	private LocalDateTime startTime;
	
	@Column
	private LocalDateTime endTime;
	
	@Column
	private String calCreated;
	
}
