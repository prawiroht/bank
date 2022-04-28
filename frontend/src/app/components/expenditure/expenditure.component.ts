import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
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
  }

  constructor(
    private expenditureService : ExpenditureService,
    private fundService : FundService,
    private bankService : BankService,
    private purchaseService : PurchaseService,
    private accountTypeService : AccountTypeService,
    private messageService : MessageService,

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
      expenditureId: (this.action == 'edit' && param == 'click') ? this.row.expenditureId : 0,
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
      status:''
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



}
