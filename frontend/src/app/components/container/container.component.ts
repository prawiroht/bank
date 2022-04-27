import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css'],
  providers: [ConfirmationService],
})
export class ContainerComponent implements OnInit {

  containers: [] = [];
  first = 0;
  rows = 10; 
  searchVal = '';
  display: boolean = false;
  action: string = '';
  submitted: boolean = false;
  bankName: string = '';
  accountNumber: number = 0;

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
    private confirmationService: ConfirmationService
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

}
