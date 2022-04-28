import { Component, OnInit } from '@angular/core';
import { GiroService } from 'src/app/services/giro.service';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-giro',
  templateUrl: './giro.component.html',
  styleUrls: ['./giro.component.css']
})
export class GiroComponent implements OnInit {
  page : number = 0;
  giros: any;
  first = 0;
  rows = 10;
  keyword = '';

  constructor(
    private giroService : GiroService,
    private messageService : MessageService,
    private confirmationService : ConfirmationService, 
    
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
    if (this.giros != null){
      if(this.giros.length < this.rows){
        return true;
      }
      else{
        return (this.giros.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.giros?this.first === 0 : true;
  }

  loadData(){
    this.giroService.getGiro().subscribe(
      res => {
        console.log(res.data)
        this.giros = res.data;
      }
    )
  }

  searchByAllCategories(keyword: string): void {
    this.giroService.getAllCategories(this.keyword).subscribe((res) => {
      console.log(res);
      this.giros = res;
      if (res.length == 0) {
        this.messageService.add({
          severity: 'warn',
          summary: 'No result',
          detail: 'The search key was not found in any record!',
        });
      }
    });
  }

}
