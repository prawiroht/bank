import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-approval-transaction-penampungan',
  templateUrl: './approval-transaction-penampungan.component.html',
  styleUrls: ['./approval-transaction-penampungan.component.css']
})
export class ApprovalTransactionPenampunganComponent implements OnInit {

  page: number = 0;
  data: any;
  first = 0;
  rows = 10;
  searchVal = '';
  constructor() { }

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
  }

}
