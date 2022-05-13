import { Component, OnInit } from '@angular/core';
import { BankService } from 'src/app/services/bank.service';
import { ContainerService } from 'src/app/services/container.service';

@Component({
  selector: 'app-dashboard-container',
  templateUrl: './dashboard-container.component.html',
  styleUrls: ['./dashboard-container.component.css']
})
export class DashboardContainerComponent implements OnInit {
  startDate='';
  endDate='';
  transactions:any;
  first=0;
  rows=10;
  data: any;
  chartOptions: any;
  keyword='';
  banks:any;
  bankName='';
  datas:any;
  bankId=0;
  dataBanks:any;
  approved:any;
  mandiri:any;
  bri:any;
  bca:any;
  bni:any;
  totals:any;
  opt = [
    {label: "All", value: "All"}
  ]
  // subscription: Subscription;
  // config: AppConfig;

  row:any = {
    bankId:0,
    bankName:''
  }

  constructor(private bankService: BankService,
              private containerService: ContainerService) { }

  ngOnInit(): void {
    // this.row={...row};
    console.log(this.dataBanks,'databanks');
    console.log(this.dataBank, 'databank');
    this.data={
      labels: ['Mandiri','BNI','BCA','BRI'],
      datasets: [
        {
          data: [3000000, 0, 0, 0],
          backgroundColor: [
            "#42A5F5",
            "#66BB6A",
            "#FFA726",
            "#23FFA7"
          ],
          hoverBackgroundColor: [
            "#64B5F6",
            "#81C784",
            "#FFB74D",
            "#23FFB7"
          ]
        }
      ]
    }
  this.getData();
  this.getBank();
  this.approvedContainer();
  this.total();
  
  console.log(this.row.bankName, 'bankname')
  // console.log(this.banks);
  // console.log(this.getBank());
  }

dataBank(row:any){
  this.row={...row};
  this.dataBanks = this.row.bankName;
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

getData(){
  if(this.startDate===null && this.endDate===null){
    this.datas = this.approved;
  }
  else{
  console.log(this.startDate, 'start');
    console.log(this.endDate, 'end');
    this.containerService.getContainerByDateAndBank(this.startDate, this.endDate, this.bankId).subscribe((res) => {
    this.datas = res.data;
    console.log(this.datas, 'datas')
  }  
)}}

approvedContainer(){
  this.containerService.getApprovedContainer().subscribe((res) => {
    this.approved = res.data;
})
}

formatter = new Intl.NumberFormat('en-ID', {
  style: 'currency',
  currency: 'IDR',
});

total(){
  this.containerService.getContainerByDateAndBank('2020-01-01', '2022-12-31', 1).subscribe((res)=>{
    this.containerService.getContainerByDateAndBank('2020-01-01', '2022-12-31', 2).subscribe((resu)=>{
      this.containerService.getContainerByDateAndBank('2020-01-01', '2022-12-31', 3).subscribe((result)=>{
        this.containerService.getContainerByDateAndBank('2020-01-01', '2022-12-31', 4).subscribe((results)=>{
          this.mandiri=this.formatter.format(res);
          this.bni=this.formatter.format(resu);
          this.bca=this.formatter.format(result);
          this.bri=this.formatter.format(results);
          this.totals=this.formatter.format(res+resu+result+results)
          console.log(this.totals)
        })
      })
    })
  })
}

}
