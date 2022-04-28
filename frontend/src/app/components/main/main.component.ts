import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { MainService } from 'src/app/services/main.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
  providers: [ConfirmationService, MessageService]
})
export class MainComponent implements OnInit {

  mains: [] = [];
  first = 0;
  rows = 10;
  searchVal = '';
  keyword = '';

  constructor(
    private mainService : MainService,
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
    if (this.mains != null){
      if(this.mains.length < this.rows){
        return true;
      }
      else{
        return (this.mains.length - this.first <= this.rows);
      }
    }
    return true;
  }

  isFirstPage():boolean{
    return this.mains?this.first === 0 : true;
  }


  loadData(){
    this.mainService.getMain().subscribe(
      {
        next: (data)=>{
          // console.log(data)
          this.mains=data.data
            //this.onReset();
        },
        error: (err) => {
          console.log('error cuy')
        }
      }
    )
  }

  searchMainByAllCategories(keyword:string): void {
    this.mainService.getMainByAllCategories(keyword).subscribe(
      res => {
        this.mains=res.data;
        if(res.data.length==0){
          this.messageService.add({
            severity: 'warn',
            summary: 'No result',
            detail: 'The search key was not found in any record!',
          });
        }
      }
    );
  
  }

}
