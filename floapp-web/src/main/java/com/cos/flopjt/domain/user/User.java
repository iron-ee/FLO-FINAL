package com.cos.flopjt.domain.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence 
	private Integer id;
	
	@Column(nullable = false, length = 100, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100) //12345 -> 해쉬(비밀번호 암호화)
	private String password;
	
	private String email;
	
	@Enumerated(EnumType.STRING) //Enum 타입을 DB에 String(VARCHAR)으로 저장
	private RoleType role; //ADMIN, USER - enum
	

	@CreationTimestamp //자동으로 현재시간이 들어감
	private Timestamp createDate;
}
