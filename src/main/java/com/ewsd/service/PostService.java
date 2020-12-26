package com.ewsd.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ewsd.config.persistence.HibernateConfig;

import com.ewsd.dto.PostDto;
import com.ewsd.model.Post;
import com.ewsd.repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;
	private HibernateConfig hibernateConfig;

	public PostService(HibernateConfig hibernateConfig) {
		this.hibernateConfig = hibernateConfig;
	}

	@Transactional
	public void add(Post postEntity) {

		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		try {
			session.save(postEntity);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public boolean exists(String title) {
		if (findByPostTitle(title) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Post findByPostTitle(String postTitle) {
		return postRepository.findByTitle(postTitle);
	}

	public void update(Post post) {
		postRepository.save(post);
	}

	public List<Post> getAll() {
		return (List<Post>) postRepository.findAll();
	}
	
	  public List<PostDto> getAll(long userId) {
	  
	  var session = hibernateConfig.getSession();
	  var transaction = session.getTransaction();
	  
	  if (!transaction.isActive()){ 
		  transaction = session.beginTransaction(); 
		  }
	  
	  CriteriaBuilder cb = session.getCriteriaBuilder(); 
	  CriteriaQuery<Post> postCQ = cb.createQuery(Post.class); 
	  Root<Post> root = postCQ.from(Post.class);
	  postCQ.select(root); 
	  var query = session.createQuery(postCQ);
	  
	  List<PostDto> resultList = new ArrayList<>();
	  
	//  System.out.println(resultList);
	  return resultList; 
	  }
	 
}
