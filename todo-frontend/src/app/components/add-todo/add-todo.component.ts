import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TodosService} from "../../services/todos.service";
import {TodoModel} from "../../model/todo.model";
import {TodoCreateModel} from "../../model/todoCreate.model";

@Component({
  selector: 'app-add-todo',
  templateUrl: './add-todo.component.html',
  styleUrls: ['./add-todo.component.scss']
})
export class AddTodoComponent implements OnInit{

  addTodoForm!: FormGroup;
  constructor(public todoService: TodosService, public dialogRef: MatDialogRef<AddTodoComponent>) {
  }

  ngOnInit(): void {
    this.addTodoForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      dueDate: new FormControl('', [Validators.required]),
    })
  }

  onSubmit(){
    let todo: TodoCreateModel = {
      title: this.addTodoForm.get('title')?.value,
      description: this.addTodoForm.get('description')?.value,
      dueDate: new Date(this.addTodoForm.get('dueDate')?.value),
    };

    this.todoService.addToDo(todo).subscribe(res => {console.log(res)});
    this.dialogRef.close();
  }



}
