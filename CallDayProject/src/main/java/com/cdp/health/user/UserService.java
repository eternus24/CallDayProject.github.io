package com.cdp.health.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdp.health.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create (String userName,String password,String email) {
		
		SiteUser user = new SiteUser();
		user.setUserName(userName);
		user.setEmail(email);
		
		//��й�ȣ ��ȣȭ
		//BCrypt�ؽ� �Լ�
		//BCryptPasswordEncoder passwordEncoder = 
		//		new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));		
		
		userRepository.save(user);
		
		return user;
	}
	
	public SiteUser getUser(String userName) {
		
		Optional<SiteUser> siteUser = 
				userRepository.findByUserName(userName);
		
		if(siteUser.isPresent()) {
			return siteUser.get();
		}else {
			throw new DataNotFoundException("User Not Found");
		}
		
	}

}














