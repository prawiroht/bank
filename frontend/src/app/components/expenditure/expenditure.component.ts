import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { formatToString } from 'rupiah-formatter/lib';
import { AccountTypeService } from 'src/app/services/account-type.service';
import { BankService } from 'src/app/services/bank.service';
import { ExpenditureService } from 'src/app/services/expenditure.service';
import { FundService } from 'src/app/services/fund.service';
import { PurchaseService } from 'src/app/services/purchase.service';

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

  funds: any;
  keyword = '';
  action = '';
  submitted = false;
  display = false;
  fundId = 0;
  banks: any;
  purchases: any;
  accountTypes: any;
  selectedMutation: any;
  statusId: any;
  statusName: any;
  mutations =[
    {label :'Debet', value: 'Debet'},
    {label :'Credit', value: 'Credit'}
  ]

  row: any = {
    mainId: 0,
    bankName: '',
    accountNumber: '',
    accountTypeName: '',
    mutationId: '',
    transactionDate: '',
    value: 0,
    purchaseName: '',
    fundName: '',
    description: '',
    statusId:0,
    statusName:'',
  }

  constructor(
    private expenditureService : ExpenditureService,
    private fundService : FundService,
    private bankService : BankService,
    private purchaseService : PurchaseService,
    private accountTypeService : AccountTypeService,
    private messageService : MessageService,
    private confirmationService : ConfirmationService,

  ) { }

  ngOnInit(): void {
    this.loadData();
    this.selectedMutation = this.mutations[0];
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
    this.expenditureService.getExpenditure().subscribe(
      {
        next: (data) => {
          this.expenditures=data.data
        },
        error: (err) => {
          console.log('error')
        }
      }
    )
    this.funds=this.getFundName();
    this.banks=this.getBankName();
    this.purchases=this.getPurchaseName();
    this.accountTypes=this.getAccountTypeName();

  }

  searchExpenditureByAllCategories(keyword:string): void {
    this.expenditureService.getExpenditureByAllCategories(keyword).subscribe(
      res => {
        this.expenditures=res.data;
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
      expenditureId: (this.action == 'Edit' && param == 'click') ? this.row.expenditureId : 0,
      bankId:0,
      bankName: '',
      accountNumber:0,
      accountName:'',
      mutationId: '',
      transactionDate:'',
      value:0,
      purchaseId:0,
      purchase:'',
      fundInd:0,
      fundName:'',
      description:'',
      statusId:0
    };
  }

  getFundName(){
    this.fundService.getFund().subscribe(
      {
        next: (data) =>{
          this.funds=data.data
          console.log(data.data)
        },

        error: (err) => {
          console.log('error cuy');
        }
      }
    )
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

  getPurchaseName(){
    this.purchaseService.getPurchase().subscribe(
      {
        next: (data) => {
          this.purchases=data.data
        },

        error: (err) => {
          console.log('error cuy')
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

  formatRupiah(nominal:number){
    return formatToString(nominal);
  }

  handleValidation() {
    if (this.row.bankId == 0 ||
      this.row.accountNumber.length == 0 ||
      this.row.accountTypeId == 0 ||
      this.row.mutationId == 0 ||
      this.row.transactionDate == null ||
      this.row.value <= 0 ||
      this.row.purchaseId == 0 ||
      this.row.fundId == 0) {
      return true;
    }
    else {
      return false;
    }
  }
  
  handleSaveExpenditure(event: any) {
    this.submitted = true;
    if (this.handleValidation()){
      return;
    }

    this.confirmationService.confirm({
      header: 'Confirmation',
      message: 'Are you sure that you want to perform this action?',
      accept: () => {
        if (this.row.expenditureId === 0 || this.row.expenditureId === null) {
          this.row.expenditureId = null;
          console.log(this.row.data, 'pppp')
          this.expenditureService.postExpenditure(this.row).subscribe({
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
          this.expenditureService.putExpenditure(this.row).subscribe({
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

  openEdit(row: any) {
    this.row = { ...row };
    this.display = true;
    this.action = 'Edit';
    console.log(this.row, 'sss')
  }


}
