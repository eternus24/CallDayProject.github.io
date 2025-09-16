package com.cdp.health.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HealthDTO {
	
	
	
	
	
	
	// Exercise DTO
	
	
	private String exSubject;
	private String exContent;
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;

    private int exId;            
    private String exPart;        // 운동 부위 
    private String exName;        // 운동 이름
    private String effect;        // 운동 효과
    private String method;        // 운동 방법
	
}
