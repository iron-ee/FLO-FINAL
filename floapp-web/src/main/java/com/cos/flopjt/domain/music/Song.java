package com.cos.flopjt.domain.music;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.cos.flopjt.domain.reply.Reply;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Song {

	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence 
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String title;
	
	@Column(nullable = false) 
	private String artist;
	
	@Enumerated(EnumType.STRING) 
	private CategoryType category;
	
	
	@Lob
	private String lyrics;

	private String relaseDate;
	
	private String img;
	
		
	@Column(nullable = false)
	private String file;
	
	
	// 양방향 매핑
	@OneToMany(mappedBy = "song", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"song"})
	@OrderBy("id desc")
	private List<Reply> replys;
}