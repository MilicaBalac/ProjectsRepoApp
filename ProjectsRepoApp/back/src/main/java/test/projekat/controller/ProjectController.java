package test.projekat.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.projekat.dto.ProjectDTO;
import test.projekat.model.Project;
import test.projekat.service.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	 @GetMapping(value = "api/projects")
	    public ResponseEntity<List<ProjectDTO>> getProjectPage(
	    		@RequestParam(defaultValue = "", name = "name") String name,
	    		Pageable page) {
	        final Page<Project> dishes = projectService.findByNameContains(name, page);
	        final List<ProjectDTO> retVal = dishes.getContent().stream()
	        		.map(ProjectDTO::new).collect(Collectors.toList());
	        return new ResponseEntity<>(retVal, HttpStatus.OK);
	    }
	 
	 @GetMapping(value = "api/projects/{id}")
	 public ResponseEntity<ProjectDTO> getProject (@PathVariable Long id) {
		 Project project = projectService.findOne(id);
		 
		 if(project == null) {
			 return new ResponseEntity<ProjectDTO>(HttpStatus.NOT_FOUND);
		 } else {
			 return new ResponseEntity<ProjectDTO>((new ProjectDTO(project)), HttpStatus.OK);
		 }
	 }
	 @PreAuthorize("isAuthenticated()")
	 @PostMapping(value = "api/projects")
	 public ResponseEntity<ProjectDTO> create (@RequestBody ProjectDTO projectDTO) {
		 
		 Project project = new Project();
		 
		 project.setName(projectDTO.getName());
		 project.setDescription(projectDTO.getDescription());
		
		 projectService.save(project);
		 ProjectDTO retVal = new ProjectDTO(project);
		 
		 return new ResponseEntity<ProjectDTO>(retVal, HttpStatus.CREATED);
	 }
	 @PreAuthorize("isAuthenticated()")
	 @PutMapping(value = "api/projects/{id}")
	 public ResponseEntity<ProjectDTO> update (@RequestBody ProjectDTO projectDTO,@PathVariable Long id) {
		 
		 Project project = projectService.findOne(id);
		  if(project == null) {
			  return new ResponseEntity<ProjectDTO>(HttpStatus.NOT_FOUND);
		  }
		  
		 project.setId(id);
		 project.setName(projectDTO.getName());
		 project.setDescription(projectDTO.getDescription());
		 project.setReadme(projectDTO.getReadme());
		 projectService.save(project);
		 ProjectDTO retVal = new ProjectDTO(project);
		 
		 return new ResponseEntity<ProjectDTO>(retVal, HttpStatus.CREATED);
	 }
	 @PreAuthorize("isAuthenticated()")
	 @DeleteMapping(value = "api/projects/{id}")
	 public ResponseEntity<Void> delete (@PathVariable Long id) {
		 
		 Project project = projectService.findOne(id);
		 
		  if(project == null) {
			  return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		  } else {
			  projectService.delete(id);
			  return new ResponseEntity<Void>(HttpStatus.OK);
		  }
	 }
	
}
