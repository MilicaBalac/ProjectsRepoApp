import { Component, OnInit } from '@angular/core';
import { Project } from '../model/Project';
import { ProjectService } from '../project.service';
import { AuthenticationService } from '../security-service/authentication.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  projects: Project[] = [];
  project: Project = {
    name: '',
    description: '',
    readme: ''
  }
  page = 0;
  name = "";
  isAdministrator: boolean;
  constructor(private projectService: ProjectService, private authServ: AuthenticationService) { }

  ngOnInit() {
    this.isAdministrator=this.authServ.getCurrentUser() ?
    this.authServ.getCurrentUser().roles.indexOf('ROLE_ADMIN') !== -1 : false;
    this.isLoggedIn();

    this.loadData();
  }
  isLoggedIn(): boolean{
    return this.authServ.isLoggedIn();
  }

  loadData(){
    this.projectService.getAll(this.page, this.name).subscribe(data => this.projects = data);
  }

  nextPage() {
    this.page += 1;
    this.loadData();
  }

  prevPage() {
    if (this.page >= 0) {
      this.page -= 1;
      this.loadData();
    }
  }
  search() {
    this.loadData();
    this.name="";
  }
  delete(id: number) {
  this.projectService.delete(id).subscribe(res => this.loadData());  
  }

  markProjectForEdit(project: Project){
    this.project = {
      id: project.id,
      name: project.name,
      description: project.description,
      readme: project.readme
    }
  }

  edit(project: Project){
    this.projectService.edit(project).subscribe(res=> this.loadData());
  }

  add(project: Project){
    this.projectService.add(project).subscribe(res=> this.loadData())
  }
  save(){
    if(this.project.id){
      this.edit(this.project)
    } else {
      this.add(this.project)
    }
  }

}
