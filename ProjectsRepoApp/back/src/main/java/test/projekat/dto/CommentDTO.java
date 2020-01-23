package test.projekat.dto;

import org.springframework.stereotype.Component;

import test.projekat.model.Comment;

@Component
public class CommentDTO {

	private Long id;
	private String userName;
	private String text;
	private ProjectDTO projectDTO;
	
	public CommentDTO() {
		
	}
	public CommentDTO(Comment comment) {
		this.id=comment.getId();
		this.userName=comment.getUserName();
		this.text=comment.getText();
		this.projectDTO= new ProjectDTO(comment.getProject());
	}
	public CommentDTO(Long id, String userName, String text, ProjectDTO projectDTO) {
		super();
		this.id = id;
		this.userName = userName;
		this.text = text;
		this.projectDTO = projectDTO;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}
	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}
	
}
