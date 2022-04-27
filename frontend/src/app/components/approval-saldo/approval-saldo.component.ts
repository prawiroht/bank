import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-approval-saldo',
  templateUrl: './approval-saldo.component.html',
  styleUrls: ['./approval-saldo.component.css']
})
export class ApprovalSaldoComponent implements OnInit {

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
    // this.dataervice.getUser(page).subscribe(
    //   res => {
    //     console.log(res.data)
    //     this.data = res.data;
    //   }
    // )
  }
}
