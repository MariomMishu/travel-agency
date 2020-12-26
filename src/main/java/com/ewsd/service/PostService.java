package com.ewsd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ewsd.config.persistence.HibernateConfig;

import com.ewsd.exceptions.ResourceNotFoundException;
import com.ewsd.dto.PostDto;
import com.ewsd.model.Post;
import com.ewsd.repositories.PostRepository;

@Service
public class PostService{
	@Autowired
	private PostRepository postRepository;
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
	
	  public List<PostDto> getAll(String visibility) {
	  
	  var session = hibernateConfig.getSession();
	  var transaction = session.getTransaction();
	  
	  if (!transaction.isActive()){ 
		  transaction = session.beginTransaction(); 
		  }
	  
	  CriteriaBuilder cb = session.getCriteriaBuilder(); 
	  CriteriaQuery<Post> postCQ = cb.createQuery(Post.class); 
	  Root<Post> root = postCQ.from(Post.class);
	  //postCQ.where(cb.equal(root.get("visibility"), visibility));
	  postCQ.select(root); 
	  var query = session.createQuery(postCQ);
	  
	  List<PostDto> resultList = new ArrayList<>();
	  
	//  System.out.println(resultList);
	  return resultList; 
	  }
	  public List<PostDto> getAllPostDtoWithUserId(long userId) {
		  
		  var session = hibernateConfig.getSession();
		  var transaction = session.getTransaction();
		  
		  if (!transaction.isActive()){ 
			  transaction = session.beginTransaction(); 
			  }
		  
		  CriteriaBuilder cb = session.getCriteriaBuilder(); 
		  CriteriaQuery<Post> postCQ = cb.createQuery(Post.class); 
		  Root<Post> root = postCQ.from(Post.class);
		  postCQ.where(cb.equal(root.get("userId"), userId));
		  postCQ.select(root); 
		  var query = session.createQuery(postCQ);
		  
		  List<PostDto> resultList = new ArrayList<>();
		  
		  try { 
			  
			  query.getResultList().forEach(post -> {
				  PostDto dto = new PostDto(); 
				  BeanUtils.copyProperties(post,dto);
				
				  
				  resultList.add(dto);
				  
		  
			  	});
		  
			  }catch (HibernateException e){ e.printStackTrace(); }
		  finally {
				  session.close(); 
			  }
		//  System.out.println(resultList);
		  return resultList; 
		  }
		public Post getById(long postId) {

			var session = hibernateConfig.getSession();
			var transaction = session.getTransaction();
			if (!transaction.isActive()) {
				transaction = session.beginTransaction();
			}
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Post> sc = cb.createQuery(Post.class);
			Root<Post> root = sc.from(Post.class);
			sc.select(root);
			sc.where(
					cb.and(
							cb.equal(root.get("id"), postId),
							cb.isTrue(root.get("isDelete"))
					)
			);
			var query = session.getEntityManagerFactory().createEntityManager().createQuery(sc);
			var post_list = query.getResultList();

			return Optional.ofNullable(post_list.get(0))
					.orElseThrow(() -> new ResourceNotFoundException("Post Not Found With This Id"));
		}
		  public void edit(Post post) {
			  postRepository.save(post);
		    }
}
