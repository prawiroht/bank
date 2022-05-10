import { Component, OnInit } from '@angular/core';
import { GiroService } from 'src/app/services/giro.service';
import { BankService } from 'src/app/services/bank.service';
import { AccountTypeService } from 'src/app/services/account-type.service';
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
    private bankService : BankService,
    private accountTypeService : AccountTypeService,
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
    this.banks=this.getBankName();
    this.accountTypes=this.getAccountTypeName();
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

  showDialog(action: string) {
    this.display = true;
    this.action = action;
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
    };
  }

  getBankName(){
    this.bankService.getBank().subscribe(
      {
        next: (data) => {
          this.banks=data.data
        },

        error: (err) => {
          console.log('error cuy');
        }
      }
    )
  }

  getAccountTypeName(){
    this.accountTypeService.getAccountType().subscribe(
      {
        next: (data) => {
          this.accountTypes=data.data
        },

        error: (err) => {
          console.log('error cuy')
        }
      }
    )
  }

  openEdit(row: any) {
    this.row = { ...row };
    this.display = true;
    this.action = 'edit';
  }

}
