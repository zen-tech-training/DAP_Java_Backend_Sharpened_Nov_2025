package com.zensar.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zensar.entity.UserEntity;
import com.zensar.repos.UserRepo;

@Service
public class DBUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntity> userEntityList = userRepo.findByUsername(username);
		if(userEntityList==null || userEntityList.size()==0) { //Invalid user
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = userEntityList.get(0);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		String strRoles = userEntity.getRoles(); //ROLE_ADMIN,ROLE_USER
		String []roles = strRoles.split(",");
		for(String role: roles) {
			authorities.add(new SimpleGrantedAuthority(role.trim()));
		}
		User user = new User(userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()),
				authorities);
		return user;
	}

}
