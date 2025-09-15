package com.cdp.health.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		
		return "signup_form";
		
	}
	
	@PostMapping("/signup")
	public String signup(@Validated UserCreateForm userCreateForm,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!userCreateForm.getPassword1()
				.equals(userCreateForm.getPassword2())) {
			
			bindingResult.rejectValue("password2", "passwordInCorrect",
					"2���� �н����尡 ��ġ���� �ʽ��ϴ�");
			
			return "signup_form";			
		}
		
		
		try {
		
		userService.create(userCreateForm.getUserName(),
				userCreateForm.getPassword1(), 
				userCreateForm.getEmail());
		
		}catch(DataIntegrityViolationException e) {
			
			bindingResult.reject("signupFailed","�̹� ��ϵ� ������Դϴ�");
			
			return "signup_form";
			
		}catch(Exception e) {
			
			bindingResult.reject("signupFailed",e.getMessage());
			
			return "signup_form";
		}
				
		return "redirect:/";
		
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login_form";
	}
	
	
}







