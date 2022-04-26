import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  mains: [] = [];
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
    if (this.mains != null){
      if(this.mains.length < this.rows){
        return true;
      }
      else{
        return (this.mains.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.mains?this.first === 0 : true;
  }

  loadData(){

  }

}
