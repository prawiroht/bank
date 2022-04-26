import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-expenditure',
  templateUrl: './expenditure.component.html',
  styleUrls: ['./expenditure.component.css']
})
export class ExpenditureComponent implements OnInit {

  expenditures: [] = [];
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
    if (this.expenditures != null){
      if(this.expenditures.length < this.rows){
        return true;
      }
      else{
        return (this.expenditures.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.expenditures?this.first === 0 : true;
  }

  loadData(){

  }

}
