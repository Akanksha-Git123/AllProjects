package com.payingguest.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payingguest.app.entities.PgImage;

@Repository
public interface PgImageRepository extends JpaRepository<PgImage, Integer> {

	/*
	 * @Query("SELECT pi.imageUrl FROM PgImage pi WHERE pi.pg.pgId = :pgId")
	 * List<String> findImageUrlsByPgId(@Param("pgId") Integer pgId);
	 */  
	/*
	 * @Query("SELECT pi FROM PgImage pi WHERE pi.pg.pgId = :pgId") List<PgImage>
	 * findByPgId(@Param("pgId") Integer pgId);
	 */
	
    List<PgImage> findByPg_PgId(Integer pgId); // Note: PgListing must have pgId field

    
    @Query(value = "SELECT * FROM pg_images WHERE pg_id = :pgId", nativeQuery = true)
    List<PgImage> findImagesByPgIdNative(@Param("pgId") Integer pgId);
}
