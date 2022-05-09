import { Component, OnInit } from '@angular/core';
import { ExpenditureService } from 'src/app/services/expenditure.service';
import {formatToString} from 'rupiah-formatter';

@Component({
  selector: 'app-approval-transaction-pengeluaran',
  templateUrl: './approval-transaction-pengeluaran.component.html',
  styleUrls: ['./approval-transaction-pengeluaran.component.css']
})
export class ApprovalTransactionPengeluaranComponent implements OnInit {

  page: number = 0;
  data: any;
  first = 0;
  rows = 10;
  searchVal = '';
  constructor(
    private expenditureService : ExpenditureService,
  ) { 
    
  }

  ngOnInit(): void {
    this.loadData(this.page);
  }

  next() {
    this.first = this.first + this.rows;
  }

  prev() {
    this.first = this.first - this.rows;
  }

  reset() {
    this.first = 0;
  }

  isLastPage(): boolean {
    return this.data ? this.first === (this.data.length - this.rows) : true;
  }

  isFirstPage(): boolean {
    return this.data ? this.first === 0 : true;
  }

  loadData(page: number) {
    this.expenditureService.getRequestedExpenditure().subscribe(
      {
        next: (data) => {
          this.data=data.data
          console.log(this.data);
          
        },
        error: (err) => {
          console.log('error')
        }
      }
    )
  }

  formatRupiah(nominal:number){
    return formatToString(nominal);
  }
}
