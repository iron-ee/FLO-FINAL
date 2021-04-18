package com.cos.flopjt.domain.playlist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaylistRepository extends JpaRepository<PlaySong, Integer>{

	@Query(value ="select * FROM playsong where userId=:userId", nativeQuery = true)
	List<PlaySong> mfindPlaysongAllByUserId(int userId);
	
}
