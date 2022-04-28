import { Component, OnInit } from '@angular/core';
import { BankService } from 'src/app/services/bank.service';

@Component({
  selector: 'app-dashboard-container',
  templateUrl: './dashboard-container.component.html',
  styleUrls: ['./dashboard-container.component.css']
})
export class DashboardContainerComponent implements OnInit {
  startDate:any;
  endDate:any;
  transactions:any;
  first=0;
  rows=10;
  data: any;
  chartOptions: any;
  keyword='';
  banks:any;
  bankName='';
  // subscription: Subscription;
  // config: AppConfig;


  constructor(private bankService: BankService) { }

  ngOnInit(): void {
    this.data={
      labels: ['Bank A', 'Bank B', 'Bank C'],
      datasets: [
        {
          data: [300, 50, 100],
          backgroundColor: [
            "#42A5F5",
            "#66BB6A",
            "#FFA726"
          ],
          hoverBackgroundColor: [
            "#64B5F6",
            "#81C784",
            "#FFB74D"
          ]
        }
      ]
    }
  this.getBank();
  // console.log(this.banks);
  // console.log(this.getBank());
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
