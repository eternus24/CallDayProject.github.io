package com.cdp.health.exercise.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.cdp.health.dto.ExerciseDTO;
import com.cdp.health.exercise.service.ExerciseService;

//Controller -> Service -> Mapper 흐름


@Controller
public class ExerciseController {

	public final ExerciseService exerciseService;

	public ExerciseController(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}


	// 운동부위 특이사항 
	// 부위별 운동 불러오기 일단 링크로 넘어가는 코딩으로 구현중  
	// 근데 나중에 겟으로 불러올때 포이치문 사용한다면
	// 변경될 수 있음 
	@GetMapping("/exPart")
	public String getExerciseByPart(
			String part,Model model){

		List<ExerciseDTO> exList = exerciseService.getExercisesByPart(part);

		model.addAttribute("part",part);
		model.addAttribute("exList",exList);

		return "health/exList";

	}
	
	// 운동
	@GetMapping("/exDetail")
	public String getExerciseDetail(int exId,Model model) {

		// 1. 서비스에서 운동하나 가져와서 
		ExerciseDTO dto = exerciseService.getExerciseById(exId);

		// 2. 모델에 담아서 JSP/Thymeleaf로 넘김
		model.addAttribute("exercise",dto.getExId());

		return "health/exDetail";

	}

}




