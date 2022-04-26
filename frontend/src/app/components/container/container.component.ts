import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  containers: [] = [];
  first = 0;
  rows = 10;
  searchVal = '';

  constructor() { }

  ngOnInit(): void {
    this.loadData();
  }

  next(){
    this.first = this.first + this.rows;
  }

  prev(){
    this.first = this.first - this.rows;
  }

  reset(){
    this.first = 0;
  }

  isLastPage(): boolean{
    if (this.containers != null){
      if(this.containers.length < this.rows){
        return true;
      }
      else{
        return (this.containers.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.containers?this.first === 0 : true;
  }

  loadData(){

  }

}
