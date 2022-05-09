import { Component, OnInit } from '@angular/core';
import { GiroService } from 'src/app/services/giro.service';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-giro',
  templateUrl: './giro.component.html',
  styleUrls: ['./giro.component.css']
})
export class GiroComponent implements OnInit {
  giros: [] = [];
  first = 0;
  rows = 10;
  keyword = '';

  action = '';
  display = false;
  banks : any;
  submitted = false;
  accountTypes: any;

  row: any = {
    currentAccountId : 0,
    code : '',
    bankName : '',
    accountNumber : '',
    initialBalanceAccount : 0,
    initialBalanceDate : '',
  }

  constructor(
    private giroService : GiroService,
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
    if (this.giros != null){
      if(this.giros.length < this.rows){
        return true;
      }
      else{
        return (this.giros.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.giros?this.first === 0 : true;
  }

  loadData(){
    this.giroService.getGiro().subscribe(
      {
        next: (data)=>{
          this.giros=data.data
        },
        error: (err) => {
          console.log('error cuy')
        }
      }
    )
  }

  searchGiroByAllCategories(keyword:string): void {
    this.giroService.getGiroByAllCategories(keyword).subscribe(
      res => {
        this.giros=res.data;
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

  handleReset(event: any,  param: string): void {
    this.row = {
      currentAccountId: (this.action == 'edit' && param == 'click') ? this.row.currentAccountId : 0,
      code : '',
      bankName: '',
      accountNumber : '',
      accountTypeName:'',
      initialBalanceDate: '',
      initialBalanceAccount : 0

      // mutationId: '',
      // transactionDate:'',
      // value:0,
      // purchaseId:0,
      // purchase:'',
      // fundInd:0,
      // fundName:'',
      // description:'',
      // status:''
    };
  }

}
