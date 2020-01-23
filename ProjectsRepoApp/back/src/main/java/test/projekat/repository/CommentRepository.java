package test.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import test.projekat.model.Comment;
@Component
public interface CommentRepository extends JpaRepository<Comment, Long> {
	public List<Comment> findByProjectId(Long projectId);
}
