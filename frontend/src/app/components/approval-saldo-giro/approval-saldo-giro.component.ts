import { Component, OnInit } from '@angular/core';
import { formatToString } from 'rupiah-formatter';
import { GiroService } from 'src/app/services/giro.service';

@Component({
  selector: 'app-approval-saldo-giro',
  templateUrl: './approval-saldo-giro.component.html',
  styleUrls: ['./approval-saldo-giro.component.css']
})
export class ApprovalSaldoGiroComponent implements OnInit {

  page: number = 0;
  data: any;
  first = 0;
  rows = 10;
  searchVal = '';
  constructor(
    private giroService : GiroService
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
    this.giroService.getRequest().subscribe(
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
