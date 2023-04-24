import { Injectable } from '@angular/core';
import {TodoModel} from "../model/todo.model";
import {data} from "../model/todo.mock";
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {TodoCreateModel} from "../model/todoCreate.model";


const URLbase = "http://localhost:5051/api/v1/todo";
@Injectable({
  providedIn: 'root'
})
export class TodosService {

  private todosSubject = new BehaviorSubject<TodoModel[]>([]);
  public todos$: Observable<TodoModel[]> = this.todosSubject.asObservable();
  private todos = data;

  constructor(private http: HttpClient) {
  }
  getAll(): Observable<TodoModel[]> {
    return this.http.get<TodoModel[]>(URLbase);
    //this.todosSubject.next(this.todos);
  }

  delete(todoId: number): Observable<void>{
    const params = new HttpParams().set('id', todoId);
    return this.http.delete<void>(URLbase + '/delete', {params});
    //this.todos = this.todos.filter(todo => todo.id != todoId);
    //this.todosSubject.next(this.todos);
  }

  addToDo(todo: TodoCreateModel): Observable<TodoCreateModel>{
    return this.http.post<TodoCreateModel>( URLbase + "/create", todo);
    //this.todos.push(todo);
    //this.todosSubject.next(this.todos);
  }

  newId(){
    return this.todos.length + 1;
  }

  getTodoById(todoId: number): Observable<TodoModel>{
    const params = new HttpParams().set('id', todoId);
    return this.http.get<TodoModel>(URLbase + '/search/todo', {params});
  }

  replaceTodo(todo: TodoModel): Observable<TodoModel>{
    return this.http.put<TodoModel>(URLbase + '/update', todo);
  }

}
