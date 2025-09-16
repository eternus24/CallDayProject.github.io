package com.cdp.health.exercise.service;

import java.util.List;

import com.cdp.health.dto.CalendarDTO;
import com.cdp.health.dto.ExerciseDTO;

//  Controller --> Service --> Mapper 흐름

public interface ExerciseService {

	// 특정 부위 운동 목록 조회
	List<ExerciseDTO> getExercisesByPart(String part);

	// 특정 ID 운동 상세 정보 조회
	ExerciseDTO getExerciseById(int exId);

	// 운동 정보 삽입
	 void insertExercise(CalendarDTO dto);

	// 운동 정보 수정
	 void updateExercise(ExerciseDTO dto);

	// 운동 정보 삭제
	 void deleteExercise(int exId);
}