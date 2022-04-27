import { Component, OnInit } from '@angular/core';
import { DepositsService } from 'src/app/services/deposits.service';


@Component({
  selector: 'app-deposits',
  templateUrl: './deposits.component.html',
  styleUrls: ['./deposits.component.css']
})
export class DepositsComponent implements OnInit {
  page : number = 0;
  deposito: any;
  first = 0;
  rows = 10;
  searchVal = '';

  constructor(
    private depositsService :DepositsService
  ) { }

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
    if (this.deposito != null){
      if(this.deposito.length < this.rows){
        return true;
      }
      else{
        return (this.deposito.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.deposito?this.first === 0 : true;
  }

  loadData(){
    this.depositsService.getDeposits().subscribe (
      (      res: { data: any; })  => {
        console.log(res.data)
        this.deposito = res.data;
      }
    )
  }

}
