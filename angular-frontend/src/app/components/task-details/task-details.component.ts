import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Task } from './../../model/task';
import { TaskService } from './../../services/task.service';

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {

  @Input() task: Task;

  constructor(
    private route: ActivatedRoute,
    private taskService: TaskService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getTask();
  }

  getTask(): void {
     const id = +this.route.snapshot.paramMap.get('id');
     this.taskService.getTask(id)
       .subscribe(task => this.task = task);
  }

  goBack(): void {
    this.location.back();
  }

 save(): void {
    this.taskService.updateTask(this.task)
       .subscribe(() => this.goBack());
  }

}
