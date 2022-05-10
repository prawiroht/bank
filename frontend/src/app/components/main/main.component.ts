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
  statusName: any;
  statusId: any;
  displayMaximizable: any;
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
    statusName:'',
    statusId:'',
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
      mainId: (this.action == 'Edit' && param == 'click') ? this.row.mainId : 0,
      bankId:0,
      bankName: '',
      accountNumber:0,
      accountTypeName:'',
      mutationId: '',
      transactionDate:'',
      value:0,
      purchaseId:0,
      purchase:'',
      fundId:0,
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

// handleSaveMain(event:any){
  
//   this.submitted=true;
//   if(this.handleValidation() && this.action=='Add'){
//     this.confirmationService.confirm({
//       message:'Are you sure that you want to perform this action?',
//     accept: () => {
//     this.mainService.postMain(this.row).subscribe(
//       {
//       next: (data) => {
//         //console.log(data,'tes');
//         if(data.status){
//           this.messageService.add({
//             severity: 'success',
//             summary: 'Input',
//             detail: 'Data has been inserted',
//           });
//           this.loadData();
//           this.displayMaximizable=false;
          
//         }
//       },
//       error: (err) => {
//         this.messageService.add({
//           severity: 'error',
//           summary: 'Error',
//           detail: 'Could not add a new record',
//         })
//         console.log('error cuy');
//       },
//     });
//   },

//   reject: () => {
//     this.messageService.add({
//       severity: 'error',
//       summary: 'Error',
//       detail: 'Input Failed',
//     });
//   },
  
//   } );

//   }else if(this.handleValidation() && this.action=='Edit'){
//     this.confirmationService.confirm({
//       message:'Are you sure that you want to perform this action?',
//     accept: () => {
//     this.mainService.putMain(this.row).subscribe({
//       next: (data) => {
//         //console.log(data);
//         if(data.status){
//           this.messageService.add({
//             severity: 'success',
//             summary: 'Edit',
//             detail: 'Data has been edited',
//           });
          
//           this.loadData();
//           this.displayMaximizable=false;
          
//         }
//       } ,
//       error: (err) => {
//         this.messageService.add({
//           severity: 'error',
//           summary: 'Error',
//           detail: 'Could not add a new record',
//         })
//         console.log('error cuy');
//       },
//     });
//   },
//   reject: () => {
//     this.messageService.add({
//       severity: 'error',
//       summary: 'Error',
//       detail: 'Edit Failed',
//     });
//   }
// })}

// }

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
  
  handleSaveMain(event: any) {
    this.submitted = true;
    if (this.handleValidation()){
      return;
    }

    this.confirmationService.confirm({
      header: 'Confirmation',
      message: 'Are you sure that you want to perform this action?',
      accept: () => {
        if (this.row.mainId === 0 || this.row.mainId === null) {
          this.row.mainId = null;
          console.log(this.row.data, 'pppp')
          this.mainService.postMain(this.row).subscribe({
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
          this.mainService.putMain(this.row).subscribe({
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


