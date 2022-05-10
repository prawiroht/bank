import { Component, OnInit } from '@angular/core';
import { formatToString } from 'rupiah-formatter';
import { DepositsService } from 'src/app/services/deposits.service';

@Component({
  selector: 'app-approval-saldo-deposito',
  templateUrl: './approval-saldo-deposito.component.html',
  styleUrls: ['./approval-saldo-deposito.component.css']
})
export class ApprovalSaldoDepositoComponent implements OnInit {

  page: number = 0;
  data: any;
  first = 0;
  rows = 10;
  searchVal = '';
  constructor(
    private depositsService : DepositsService
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
    this.depositsService.getRequest().subscribe(
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
