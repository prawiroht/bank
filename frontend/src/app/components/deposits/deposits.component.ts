import { Component, OnInit } from '@angular/core';
import { DepositsService } from 'src/app/services/deposits.service';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-deposits',
  templateUrl: './deposits.component.html',
  styleUrls: ['./deposits.component.css']
})
export class DepositsComponent implements OnInit {
  deposito: any;
  first = 0;
  rows = 10;
  keyword = '';

  constructor(
    private depositsService :DepositsService,
    private messageService : MessageService,
    private confirmationService : ConfirmationService,
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
    this.depositsService.getDeposits().subscribe(
      {
        next: (data)=>{
          this.deposito=data.data
        },
        error: (err) => {
          console.log('error cuy')
        }
      }
    )
  }

  searchDepositsByAllCategories(keyword:string): void {
    this.depositsService.getDepositsByAllCategories(keyword).subscribe(
      res => {
        this.deposito=res.data;
        if(res.data.length==0){
          this.messageService.add({
            severity: 'warn',
            summary: 'No result',
            detail: 'The search key was not found in any record!',
          });
        }
      }
    );
  }

}