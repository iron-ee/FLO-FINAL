package com.cos.flopjt.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.flopjt.domain.playlist.PlaySong;
import com.cos.flopjt.domain.playlist.PlaylistRepository;
import com.cos.flopjt.domain.user.User;
import com.cos.flopjt.domain.user.UserRepository;
import com.cos.flopjt.web.dto.user.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	private final PlaylistRepository playlistRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User 노래보기(int id) {
			return userRepository.findById(id).orElseThrow(()->{
				return new IllegalArgumentException("id를 찾을 수 없습니다.");
			});
		}
	
	
	public List<PlaySong> 유저플레이리스트찾기(int id){
		
		return playlistRepository.mfindPlaysongAllByUserId(id);
	
	}
	
	
	
	@Transactional
	public User 회원수정(int id, UserUpdateReqDto userUpdateReqDto) {
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		String encPassword = bCryptPasswordEncoder.encode(userUpdateReqDto.getPassword());
		
		userEntity.setPassword(encPassword);
		userEntity.setEmail(userUpdateReqDto.getEmail());
		
		return userEntity;
	}
}
