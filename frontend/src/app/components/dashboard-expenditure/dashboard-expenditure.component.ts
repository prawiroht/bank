import { Component, OnInit } from '@angular/core';
import { BankService } from 'src/app/services/bank.service';

@Component({
  selector: 'app-dashboard-expenditure',
  templateUrl: './dashboard-expenditure.component.html',
  styleUrls: ['./dashboard-expenditure.component.css']
})
export class DashboardExpenditureComponent implements OnInit {
  startDate:any;
  endDate:any;
  transactions:any;
  first=0;
  rows=10;
  basicData: any;
  chartOptions: any;
  keyword='';
  banks:any;
  bankName='';
  // subscription: Subscription;
  // config: AppConfig;


  constructor(private bankService: BankService) { }

  ngOnInit(): void {
    this.basicData = {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [
          {
              label: 'My First dataset',
              backgroundColor: '#42A5F5',
              data: [65, 59, 80, 81, 56, 55, 40]
          },
          {
              label: 'My Second dataset',
              backgroundColor: '#FFA726',
              data: [28, 48, 40, 19, 86, 27, 90]
          },
          {
              label: 'My Third dataset',
              backgroundColor: '#52F725',
              data: [45, 74, 27, 44, 18, 37, 53]
          }
      ]
  };
  this.getBank();
  }

Search(){
  console.log(this.keyword);
}

getBank(){
  this.bankService.getBank().subscribe((res) => {
    this.banks = res.data;
    console.log(this.banks);
    console.log(this.banks.bankName);
})}

}
