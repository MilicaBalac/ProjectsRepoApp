package test.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.projekat.model.Comment;
import test.projekat.repository.CommentRepository;

@Component
public class CommentService {


	@Autowired
	CommentRepository commentRepository;
	
	
	public Comment save (Comment comment) {
		return commentRepository.save(comment);
	}
	
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}
	
	public List<Comment> findByProjectId(Long projectId) {
		List <Comment> comments = commentRepository.findByProjectId(projectId);
		System.out.println("dobavio iz repositorya");
		return comments;
	}
	
}
