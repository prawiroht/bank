import { Component, OnInit } from '@angular/core';
import { ContainerService } from 'src/app/services/container.service';
import {formatToString} from 'rupiah-formatter';
import { ConfirmationService, MessageService } from 'primeng/api';

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
  point:any;
  constructor(
    private containerService: ContainerService,
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
    this.containerService.getRequestedContainer().subscribe(
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

  approve(point:any){
    this.point=point;
    this.point.statusId=2;
    console.log(this.point);

    this.confirmationService.confirm({
      header: 'Confirmation',
      message: 'Are you sure that you want to perform this action?',
      accept:()=>{
        this.containerService.putContainer(this.point).subscribe({
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

  delete(id:number){
    this.confirmationService.confirm({
      header: 'Confirmation',
      message: 'Are you sure that you want to perform this action?',
      accept:()=>{
        this.containerService.deleteContainer(id).subscribe({
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

  formatRupiah(nominal:number){
    return formatToString(nominal);
  }

}
