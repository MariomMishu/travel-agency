package com.ewsd.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ewsd.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	Post findByTitle(String title);
	List<Post> findAllByVisibility(String visibility);


}
