package test.projekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import test.projekat.model.Project;

@Component
public interface ProjectRepository extends JpaRepository<Project, Long> {

	public Page<Project> findByNameContains(String name, Pageable page);
	
}
