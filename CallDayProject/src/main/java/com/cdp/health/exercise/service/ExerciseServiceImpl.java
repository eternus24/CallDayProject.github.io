package com.cdp.health.exercise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdp.health.dto.CalendarDTO;
import com.cdp.health.dto.ExerciseDTO;
import com.cdp.health.mapper.HealthMapper;

// 운동 서비스 구현체
// Mapper 호출하여 실제 데이터 처리

@Service
public class ExerciseServiceImpl implements ExerciseService {

	private final HealthMapper healthMapper;


	public ExerciseServiceImpl(HealthMapper healthMapper) {
		
		this.healthMapper = healthMapper;
	}
	
    @Override
    public List<ExerciseDTO> getExercisesByPart(String part) {
        return healthMapper.getExercisesByPart(part);
    }


	@Override
	public ExerciseDTO getExerciseById(int exId) {
		
		return healthMapper.getExerciseById(exId);
	}

	@Override
	public void insertExercise(CalendarDTO dto) {
		healthMapper.insertExercise(dto);

	}

	@Override
	public void updateExercise(ExerciseDTO dto) {
		healthMapper.updateExercise(dto);

	}

	@Override
	public void deleteExercise(int exId) {
		healthMapper.deleteExercise(exId);

	}
}


