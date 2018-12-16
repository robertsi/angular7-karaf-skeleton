import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse} from '@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import { map, tap } from '../../../node_modules/rxjs/operators';
import { Task } from './../model/task';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  private apiUrl = 'http://localhost:8181/cxf/tasks';

  getTask(id: number): Observable<Task> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Task>(url).pipe(
     // map(resp => resp['task'] as Task)
    );
  }

  getTasks(): Observable<Array<Task>> {
    return this.http.get<Array<Task>>(this.apiUrl);
    // return this.http.get(this.apiUrl).pipe(
    //   map(resp => resp['task'] as Task[])
    // );
  }

  addTask(newTask: Task): Observable<Task> {
     return this.http.post<Task>(this.apiUrl, newTask, httpOptions).pipe(
      //return this.http.post(this.apiUrl, {task: newTask}, httpOptions).pipe(
      // hack webbrowser does not follow Location header and 201 Created status code. Possible solution is to add Refresh
      // header on server side?? https://www.blackpepper.co.uk/what-we-think/blog/201-created-or-post-redirect-get
      map(resp => newTask as Task ),
      tap(task => this.log(`Task with id=${task.id} was created`))
      // ,catchError(this.handleError<Task>('addTask')
    );
  }

  deleteTask(task: Task | number): Observable<Object> {
    const id = typeof task === 'number' ? task : task.id;
    const url = `${this.apiUrl}/${id}`;

    return this.http.delete(url, httpOptions).pipe(

      // tap(_ => this.log(`deleted hero id=${id}`))
      // catchError(this.handleError<Hero>('deleteHero'))
    );
  }


  updateTask(task: Task): Observable<any> {
    const url = `${this.apiUrl}/${task.id}`;
    return this.http.put<any>(url, task, httpOptions).pipe(
      tap(_ => this.log(`updated task id=${task.id}`))
      // catchError(this.handleError<any>('updateHero'))
    );
  }

  private log(message: string) {
    console.log(message);
  }

}
