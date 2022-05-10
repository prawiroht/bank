import { Component, OnInit } from '@angular/core';
import { DepositsService } from 'src/app/services/deposits.service';
import { BankService } from 'src/app/services/bank.service';
import { PeriodService } from 'src/app/services/period.service';
import { saveAs } from 'file-saver';
import { DownloadService } from 'src/app/services/download.service';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-deposits',
  templateUrl: './deposits.component.html',
  styleUrls: ['./deposits.component.css']
})
export class DepositsComponent implements OnInit {
  deposito: [] = [];
  first = 0;
  rows = 10;
  keyword = '';

  action = '';
  display = false;
  banks : any;
  submitted = false;
  periods : any;

  row: any = {
    depositId : 0,
    code : '',
    bankName : '',
    accountNumber : '',
    period : '',
    nominal : 0,
    interest : 0,
    startDate : '',
    dueDate : '',
  }

  constructor(
    private depositsService :DepositsService,
    private messageService : MessageService,
    private bankService : BankService,
    private periodService : PeriodService,
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
    this.banks=this.getBankName();
    this.periods=this.getPeriod();
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

  getPeriod(){
    this.periodService.getPeriod().subscribe(
      {
        next: (data) => {
          this.periods=data.data
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

  handleReset(event: any,  param: string): void {
    this.row = {
      depositId: (this.action == 'edit' && param == 'click') ? this.row.depositId : 0,
      code : '',
      bankName: '',
      accountNumber : '',
      period : '',
      nominal : 0,
      interest : 0,
      startDate : '',
      dueDate : ''
    };
  }

  handleValidation() {
    if (this.row.bankId == 0 ||
      this.row.accountNumber.length == 0 ||
      this.row.periodId == 0 ||
      this.row.startDate == null ||
      this.row.dueDate == null ||
      this.row.earningInterest == 0 ||
      this.row.interest < 0 ||
      this.row.nominal <= 0 ) {
      return true;
    }
    else {
      return false;
    }
  }
  
  handleSaveDeposits(event: any) {
    this.submitted = true;
    if (this.handleValidation()){
      return;
    }

    this.confirmationService.confirm({
      header: 'Confirmation',
      message: 'Are you sure that you want to perform this action?',
      accept: () => {
        if (this.row.depositId === 0 || this.row.depositId === null) {
          this.row.depositId = null;
          console.log(this.row.data, 'pppp')
          this.depositsService.postDeposits(this.row).subscribe({
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
          this.depositsService.putDeposits(this.row).subscribe({
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