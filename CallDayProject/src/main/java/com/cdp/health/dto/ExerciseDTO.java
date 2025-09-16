package com.cdp.health.dto;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExerciseDTO {


	private int exId;
	
    private String exPart;        // 운동 부위 (chest, shoulder 등)
   
    private String exName;        // 운동 이름
    
    private String targetMuscle;  // 타겟 근육
    
    private String effect;        // 운동 효과
   
    private String method;        // 운동 방법

    private String youtubeUrl;    // 관련 유튜브 링크
	

}
