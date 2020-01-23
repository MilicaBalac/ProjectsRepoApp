import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Project } from '../model/Project';

@Component({
  selector: 'app-add-edit-form',
  templateUrl: './add-edit-form.component.html',
  styleUrls: ['./add-edit-form.component.css']
})
export class AddEditFormComponent implements OnInit {

  @Input()
  projectToAdd: Project;

  @Input()
  isAdministrator:boolean;
  
  @Output()
  savedProject: EventEmitter<Project>=new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  save(){
    console.log(this.projectToAdd);
    this.savedProject.next(this.projectToAdd);
  }

  reset(){
    this.projectToAdd = {
      name: '',
      description: '',
      readme: ''
    }
    }


}
