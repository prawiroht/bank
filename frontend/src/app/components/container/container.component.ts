import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AccountTypeService } from 'src/app/services/account-type.service';
import { BankService } from 'src/app/services/bank.service';
import { ContainerService } from 'src/app/services/container.service';
import { FundService } from 'src/app/services/fund.service';
import { PurchaseService } from 'src/app/services/purchase.service';
import {formatToString} from 'rupiah-formatter';

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
  statusId: any;
  statusName: any;
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
    statusId:'',
    statusName:'',

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
    this.accountTypes=this.getAccountTypeName();

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
      containerId: (this.action == 'Edit' && param == 'click') ? this.row.containerId : 0,
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
          console.log(data.data, 'tesss')
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
  
  handleSaveContainer(event: any) {
    this.submitted = true;
    if (this.handleValidation()){
      return;
    }

    this.confirmationService.confirm({
      header: 'Confirmation',
      message: 'Are you sure that you want to perform this action?',
      accept: () => {
        if (this.row.containerId === 0 || this.row.containerId === null) {
          this.row.containerId = null;
          console.log(this.row.data, 'pppp')
          this.containerService.postContainer(this.row).subscribe({
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
          this.containerService.putContainer(this.row).subscribe({
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
