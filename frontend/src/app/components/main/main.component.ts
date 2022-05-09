import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AccountTypeService } from 'src/app/services/account-type.service';
import { BankService } from 'src/app/services/bank.service';
import { FundService } from 'src/app/services/fund.service';
import { MainService } from 'src/app/services/main.service';
import { PurchaseService } from 'src/app/services/purchase.service';
import { saveAs } from 'file-saver';
import { DownloadService } from 'src/app/services/download.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
  providers: [ConfirmationService, MessageService]
})
export class MainComponent implements OnInit {

  mains: [] = [];
  funds: any;
  first = 0;
  rows = 10;
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
    {label :'Credit', value: 'Credit'}]

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
    private mainService : MainService,
    private messageService : MessageService,
    private confirmationService : ConfirmationService,
    private fundService : FundService,
    private bankService : BankService,
    private purchaseService : PurchaseService,
    private accountTypeService : AccountTypeService,
    private downloadService : DownloadService
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
    if (this.mains != null){
      if(this.mains.length < this.rows){
        return true;
      }
      else{
        return (this.mains.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.mains?this.first === 0 : true;
  }


  loadData(){
    this.mainService.getMain().subscribe(
      {
        next: (data)=>{
          // console.log(data)
          this.mains=data.data
            //this.onReset();
        },
        error: (err) => {
          console.log('error cuy')
        }
      }
    )

    this.funds=this.getFundName();
    this.banks=this.getBankName();
    this.purchases=this.getPurchaseName();
    this.accountTypes=this.getAccountTypeName();
  }

  searchMainByAllCategories(keyword:string): void {
    this.mainService.getMainByAllCategories(keyword).subscribe(
      res => {
        this.mains=res.data;
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
      mainId: (this.action == 'edit' && param == 'click') ? this.row.mainId : 0,
      bankId:0,
      bankName: '',
      accountNumber:0,
      accountTypeName:'',
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

//   downloadFile(data: Response) {
//     const blob = new Blob([data], { type: 'text/csv' });
//     const url = window.URL.createObjectURL(blob);
//     const anchor = document.createElement('a');
//     anchor.download = 'myfile.txt'; // here you can specify file name
//     anchor.href = url;
//     document.body.appendChild(anchor);
//     anchor.click();
//     document.body.removeChild(anchor);
// }

currentDate = new Date()
getDatetime(){
  return (this.currentDate).getDay()+"-"+(this.currentDate).getMonth()+"-"+(this.currentDate).getFullYear()+"at"+(this.currentDate).getHours()+":"+(this.currentDate).getMinutes();
   }

filename="utama_" + this.getDatetime()+".csv"
downloadFile(filename: string): void {
  this.downloadService
    .download(filename)
    .subscribe(blob => saveAs(blob, filename));
}

}


