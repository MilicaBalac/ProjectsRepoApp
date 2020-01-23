package test.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import test.projekat.model.Project;
import test.projekat.repository.ProjectRepository;

@Component
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	public Page<Project> findByNameContains (String name, Pageable page){
		return projectRepository.findByNameContains(name, page);
	}
	
	public Project findOne (Long id) {
		return projectRepository.findOne(id);
	}
	
	public Project save (Project project) {
		return projectRepository.save(project);
	}
	
	public void delete (Long id) {
		projectRepository.delete(id);
	}
}
