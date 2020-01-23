package test.projekat.dto;

import test.projekat.model.Project;

public class ProjectDTO {

	private Long id;
	private String name;
	private String description;
	private String readme;
	
	public ProjectDTO() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReadme() {
		return readme;
	}
	public void setReadme(String readme) {
		this.readme = readme;
	}
	public ProjectDTO(Long id, String name, String description, String readme) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.readme = readme;
	}
	public ProjectDTO(Project project) {
		super();
		this.id = project.getId();
		this.name = project.getName();
		this.description = project.getDescription();
		this.readme = project.getReadme();
	}
	
	
}
