import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comment } from './model/Comment';


@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private path ="api/comments"

  constructor(private http: HttpClient) { }


  save(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(this.path, comment);
  }

  findAll(id: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`api/projects/${id}/comments`);
  }

}
