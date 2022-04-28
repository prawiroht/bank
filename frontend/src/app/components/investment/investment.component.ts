import { Component, OnInit } from '@angular/core';
import { InvestmentService } from 'src/app/services/investment.service';
import { ConfirmationService, MessageService } from 'primeng/api';


@Component({
  selector: 'app-investment',
  templateUrl: './investment.component.html',
  styleUrls: ['./investment.component.css']
})
export class InvestmentComponent implements OnInit {
  investasi: any;
  first = 0;
  rows = 10;
  keyword = '';
  
  constructor(
    private investmentService : InvestmentService,
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
    if (this.investasi != null){
      if(this.investasi.length < this.rows){
        return true;
      }
      else{
        return (this.investasi.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.investasi?this.first === 0 : true;
  }

  loadData(){
    this.investmentService.getInvestment().subscribe(
      {
        next: (data)=>{
          this.investasi=data.data
        },
        error: (err) => {
          console.log('error cuy')
        }
      }
    )
  }

  searchByAllCategories(keyword:string): void {
    this.investmentService.getByAllCategories(keyword).subscribe(
      res => {
        this.investasi=res.data;
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

