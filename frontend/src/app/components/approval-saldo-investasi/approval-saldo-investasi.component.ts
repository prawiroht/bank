import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-approval-saldo-investasi',
  templateUrl: './approval-saldo-investasi.component.html',
  styleUrls: ['./approval-saldo-investasi.component.css']
})
export class ApprovalSaldoInvestasiComponent implements OnInit {

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
