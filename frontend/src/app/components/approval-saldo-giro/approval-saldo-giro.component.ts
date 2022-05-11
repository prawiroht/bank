import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
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
  point:any;
  constructor(
    private giroService : GiroService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
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

  approve(point:any){
    this.point=point;
    this.point.statusId=2;
    console.log(this.point);

    this.confirmationService.confirm({
      header: 'Confirmation',
      message: 'Are you sure that you want to perform this action?',
      accept:()=>{
        this.giroService.putGiro(this.point).subscribe({
          next: (data)=>{
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'The request has been approved!',
            });
            this.loadData(this.page);
          },
          error: (err)=>{
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'Input Failed',
            });
          }
        })
      },
      reject: () => {
      },
    });
  }

  delete(id:any){
    this.confirmationService.confirm({
      header: 'Confirmation',
      message: 'Are you sure that you want to perform this action?',
      accept:()=>{
        this.giroService.deleteGiro(id).subscribe({
          next: (data)=>{
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'The request has been approved!',
            });
            this.loadData(this.page);
          },
          error: (err)=>{
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'Input Failed',
            });
          }
        })
      },
      reject: () => {
      },
    });
  }
}
