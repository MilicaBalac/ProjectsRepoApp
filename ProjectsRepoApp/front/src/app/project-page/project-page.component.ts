import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ProjectService } from '../project.service';
import { Project } from '../model/Project';
import { Comment } from '../model/Comment';
import { CommentService } from '../comment.service';

@Component({
  selector: 'app-project-page',
  templateUrl: './project-page.component.html',
  styleUrls: ['./project-page.component.css']
})
export class ProjectPageComponent implements OnInit {

  project: Project = {
    id: 0,
    name:'',
    description:'',
    readme:''
  }

  comments: Comment[] = [];
  comment: Comment = {
    userName: "",
    text:""
  };



  constructor(private route: ActivatedRoute, private projectService: ProjectService,private commentService: CommentService ) { }

  ngOnInit() {
    this.getProject();
  }

  getProject() {
    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      this.projectService.getProject(id).subscribe(data => {
        this.project = data;

        this.loadProjectComments();
      });
    });
  }

  save() {
    this.comment.projectDTO = this.project;
    console.log("this.comment", this.comment);
    this.commentService.save(this.comment).subscribe(res => console.log('res', res));
    this.getProject();
  }

  loadProjectComments() {
    this.commentService.findAll(this.project.id).subscribe(res => {
      this.comments = res
    });
  }
 }
