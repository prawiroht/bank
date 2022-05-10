import { Component, OnInit } from '@angular/core';
import { formatToString } from 'rupiah-formatter';
import { InvestmentService } from 'src/app/services/investment.service';

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
  constructor(
    private investmentService : InvestmentService
  ) { }

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
    this.investmentService.getRequest().subscribe(
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
