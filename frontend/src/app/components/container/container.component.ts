import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AccountTypeService } from 'src/app/services/account-type.service';
import { BankService } from 'src/app/services/bank.service';
import { ContainerService } from 'src/app/services/container.service';
import { FundService } from 'src/app/services/fund.service';
import { PurchaseService } from 'src/app/services/purchase.service';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css'],
  providers: [ConfirmationService],
})
export class ContainerComponent implements OnInit {

  containers: [] = [];
  banks: any;
  funds: any;
  accountTypes: any;
  purchases: any;
  first = 0;
  rows = 10; 
  keyword = '';
  display: boolean = false;
  action: string = '';
  submitted: boolean = false;
  bankName: string = '';
  accountNumber: number = 0;
  selectedMutation: any;
  mutations =[
    {label :'Debet', value: 'Debet'},
    {label :'Credit', value: 'Credit'}]

  row: any = {
    containerId: 0,
    bankId:0,
    bankName: '',
    accountNumber:0,
    accountName:'',
    transactionDate:'',
    value:0,
    purchaseId:0,
    purchase:'',
    fundInd:0,
    fundName:'',
    description:'',
    status:''

  };


  constructor(
    private confirmationService: ConfirmationService,
    private messageService : MessageService,
    private containerService : ContainerService,
    private fundService : FundService,
    private purchaseService : PurchaseService,
    private accountTypeService : AccountTypeService,
    private bankService : BankService,

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
    if (this.containers != null){
      if(this.containers.length < this.rows){
        return true;
      }
      else{
        return (this.containers.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.containers?this.first === 0 : true;
  }


  loadData(){
    this.containerService.getContainer().subscribe(
      {
        next: (data) => {
          this.containers=data.data
        },

        error: (err) => {
          console.log('error')
        }
      }
    )
    this.banks=this.getBankName();
    this.purchases=this.getPurchaseName();
    this.funds=this.getFundName();

  }

  searchContainerByAllCategories(keyword:string): void {
    this.containerService.getContainerByAllCategories(keyword).subscribe(
      res => {
        this.containers=res.data;
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
      containerId: (this.action == 'edit' && param == 'click') ? this.row.containerId : 0,
      bankId:0,
      bankName: '',
      accountNumber:0,
      accountTypeName:'',
      transactionDate:'',
      value:0,
      purchaseId:0,
      purchase:'',
      fundInd:0,
      fundName:'',
      description:'',
      status:'',
      mutationId: ''


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
