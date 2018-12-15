import { Component, OnInit } from '@angular/core';
import { Task } from '../../model/task';
import { TaskService } from './../../services/task.service';

@Component({
  selector: 'app-tasklist',
  templateUrl: './tasklist.component.html',
  styleUrls: ['./tasklist.component.css']
})
export class TasklistComponent implements OnInit {

  constructor(private taskService: TaskService) { }

  //tasks: Task[] = [new Task({id: 1, title: `test`})];
  tasks: Task[] = [];

  ngOnInit() {
    this.getTasks();
  }

  getTasks(): void {
    // this.taskService.getTasks().subscribe(response => { this.tasks = response['task'] as Task[]; });
    this.taskService.getTasks().subscribe(tasks => { this.tasks = tasks; });
  }

  add(title: string): void {
    title = title.trim();
    if (!title) { return; }
    let id = Math.max.apply(Math, this.tasks.map(function(task) { return task.id; })) + 1;
    id = id === -Infinity ? 0 : id;

    const newTask = { id , title } as Task;
    this.taskService.addTask(newTask)
      .subscribe(task => {
        this.tasks.push(task);
      });
  }

  delete(task: Task): void {
    this.taskService.deleteTask(task).subscribe(res =>  this.tasks = this.tasks.filter(t => t !== task));
  }

}
