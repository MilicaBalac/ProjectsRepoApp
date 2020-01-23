package test.projekat.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import test.projekat.dto.CommentDTO;
import test.projekat.model.Comment;
import test.projekat.model.Project;
import test.projekat.service.CommentService;
import test.projekat.service.ProjectService;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@Autowired
	ProjectService projectService;
	
	
	@PostMapping(value = "api/comments")
	ResponseEntity<CommentDTO> save (@RequestBody CommentDTO commentDTO) {
		
		Comment comment = new Comment();
		
		comment.setId(commentDTO.getId());
		comment.setUserName(commentDTO.getUserName());
		comment.setText(commentDTO.getText());
	
		
		
		System.out.println("PROJECT ID: " + commentDTO.getProjectDTO().getId());
		Project project = projectService.findOne(commentDTO.getProjectDTO().getId());
		
		if (project == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		comment.setProject(project);
		
		Comment savedComment = commentService.save(comment);
		
		return new ResponseEntity<>(new CommentDTO(savedComment), HttpStatus.OK);
		
	}
	
	@GetMapping(value="api/projects/{id}/comments")
	ResponseEntity<List<CommentDTO>> findAll(@PathVariable Long id) {
		System.out.println("ID:: " + id);
		List<CommentDTO> comments = commentService.findByProjectId(id).stream()
				.map(CommentDTO::new)
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
}
