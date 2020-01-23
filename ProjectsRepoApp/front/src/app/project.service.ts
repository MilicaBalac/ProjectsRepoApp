import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Project } from './model/Project';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private readonly path = "api/projects";

  private pageSize = 10;
  constructor(private http: HttpClient) { }

  getAll(page: number, name: string): Observable<Project[]> {
    return this.http.get<Project[]>(this.path + `?page=${page}&size=${this.pageSize}&name=${name}`);
  }
  getProject(id: number): Observable<Project>{
    return this.http.get<Project>(`${this.path}/${id}`);
  }
  add(project: Project): Observable<Project>{
    return this.http.post<Project>(this.path, project);
  }
  edit(project: Project): Observable<Project>{
    return this.http.put<Project>(`${this.path}/${project.id}`, project);
  }
  delete(id: number): Observable<Project>{
    return this.http.delete<Project>(`${this.path}/${id}`);
  }




}
