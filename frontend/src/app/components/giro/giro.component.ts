import { Component, OnInit } from '@angular/core';
import { GiroService } from 'src/app/services/giro.service';
import { BankService } from 'src/app/services/bank.service';
import { AccountTypeService } from 'src/app/services/account-type.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { saveAs } from 'file-saver';
import { DownloadService } from 'src/app/services/download.service';

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
    private downloadService : DownloadService
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

  filename="utama_" + this.getDatetime()+".csv"
  downloadFile(filename: string): void {
  this.downloadService
    .download(filename)
    .subscribe(blob => saveAs(blob, filename));
  }

  currentDate = new Date()
  getDatetime(){
  return (this.currentDate).getDay()+"-"+(this.currentDate).getMonth()+"-"+(this.currentDate).getFullYear()+"at"+(this.currentDate).getHours()+":"+(this.currentDate).getMinutes();
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

  handleValidation() {
    if (this.row.bankId == 0 ||
      this.row.accountNumber.length == 0 ||
      this.row.accountTypeId == 0 ||
      this.row.initialBalanceDate == null ||
      this.row.initialBalanceAccount <= 0 ) {
      return true;
    }
    else {
      return false;
    }
  }
  
  handleSaveGiro(event: any) {
    this.submitted = true;
    if (this.handleValidation()){
      return;
    }

    this.confirmationService.confirm({
      header: 'Confirmation',
      message: 'Are you sure that you want to perform this action?',
      accept: () => {
        if (this.row.currentAccountId === 0 || this.row.currentAccountId === null) {
          this.row.currentAccountId = null;
          console.log(this.row.data, 'pppp')
          this.giroService.postGiro(this.row).subscribe({
            next: (data) => { 
              console.log(data);
              if (data.status) {
                this.messageService.add({
                  severity: 'success',
                  summary: 'Input',
                  detail: 'Data has been inserted',
                });
                this.loadData();
                this.display = false;
              }
            },
            error: (err) => {
              this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Could not add a new record',
              });
            },
          });
        } else {
          this.giroService.putGiro(this.row).subscribe({
            next: (data) => {
              console.log(data);
              console.log(this.row, 'mmmm')
              if (data.status) {
                console.log('jjjj')
                this.messageService.add({
                  severity: 'success',
                  summary: 'Input',
                  detail: 'Data has been updated',
                });
                this.loadData();
                this.display = false;
              }
              console.log(data.status)
            },
            error: (err) => {
              this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Could not edit a record',
              });
            },
          });
        }
      },
      reject: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Input Failed',
        });
      },
    });
  }

}
