import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TodosService} from "../../services/todos.service";
import {TodoModel} from "../../model/todo.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit-todo',
  templateUrl: './edit-todo.component.html',
  styleUrls: ['./edit-todo.component.scss']
})
export class EditTodoComponent implements OnInit{

  public todo!: TodoModel;
  public editTodoForm!: FormGroup;
  private todoId: number;
  public errMessage: String = "";
  constructor(private todoService: TodosService, private route: ActivatedRoute, private router: Router) {
    this.todoId = +this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    try{
      this.todoService.getTodoById(this.todoId).subscribe(res => {this.todo = res;
        this.editTodoForm = new FormGroup({
          title: new FormControl(this.todo.title ),
          description: new FormControl(this.todo.description),
          dueDate: new FormControl(this.todo.dueDate,),
        })
      });
    }catch (err){
      this.router.navigate(["/not-found"])
    }

  }

  onSubmit(){
    let title= this.editTodoForm.get('title')?.value;
    let description = this.editTodoForm.get('description')?.value;
    let dueDate = new Date(this.editTodoForm.get('dueDate')?.value);
    let newTodo: TodoModel = {id: this.todo.id, title: title, description: description, dueDate: dueDate, done: false};
    this.todoService.replaceTodo(newTodo).subscribe(res => this.router.navigate(['../'], {relativeTo: this.route}))
  }

}
