import { Component, OnInit } from '@angular/core';

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
  // subscription: Subscription;
  // config: AppConfig;


  constructor() { }

  ngOnInit(): void {
    this.data={
      labels: ['A','B','C'],
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
  
  }

Search(){
  console.log(this.keyword);
}

}
