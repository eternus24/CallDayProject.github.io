package com.cdp.health.exercise;

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
public class Exercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int exId;
	@Column
    private String exPart;        // 운동 부위 (chest, shoulder 등)
    @Column
    private String exName;        // 운동 이름
    @Column
    private String targetMuscle;  // 타겟 근육
    @Column
    private String effect;        // 운동 효과
    @Column
    private String method;        // 운동 방법
    @Column
    private String youtubeUrl;    // 관련 유튜브 링크

}
