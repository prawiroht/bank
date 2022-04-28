import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { FundService } from 'src/app/services/fund.service';
import { MainService } from 'src/app/services/main.service';

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
  searchVal = '';
  keyword = '';
  action = '';
  submitted = false;
  display = false;
  fundId = 0;

  row: any = {
    mainId: 0,
    bankName: '',
    accountNumber: '',
    accountTypeName: '',
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
    private fundService : FundService
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

}
