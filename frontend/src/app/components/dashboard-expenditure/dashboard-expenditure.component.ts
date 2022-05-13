import { Component, OnInit } from '@angular/core';
import { BankService } from 'src/app/services/bank.service';
import { ExpenditureService } from 'src/app/services/expenditure.service';

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
  bankId=0;
  expenditures:any;
  mandiri:any;
  bri:any;
  bca:any;
  bni:any;
  mandiriNum:number=0;
  briNum:any;
  bcaNum:any;
  bniNum:any;
  totals:any;
  datas:any;
  approved:any;
  opt = [
    {label: "All", value: "All"}
  ]
  // subscription: Subscription;
  // config: AppConfig;


  constructor(private bankService: BankService,
              private expenditureService: ExpenditureService) { }

  ngOnInit(): void {
    this.basicData = {
      labels: ['Pengeluaran'],
      datasets: [
          {
              label: 'BCA',
              backgroundColor: '#42A5F5',
              data: [this.bcaNum]
          },
          {
              label: 'BNI',
              backgroundColor: '#FFA726',
              data: [this.bniNum]
          },
          {
              label: 'BRI',
              backgroundColor: '#52F725',
              data: [this.briNum]
          },
          {
            label: 'Mandiri',
            backgroundColor: '#23FFA7',
            data: [1000000]
        }
      ]
  }
  this.getBank();
  this.getExpenditure();
  this.approvedExp();
  this.getData();
  this.total();
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

getExpenditure(){
  this.expenditureService.getExpenditure().subscribe((res) => {
    this.expenditures = res.data;
  })
  console.log(this.expenditures, 'expenditures');
}

getData(){
  if(this.startDate===null && this.endDate===null){
    this.datas = this.approved;
  }
  else{
  console.log(this.startDate, 'start');
    console.log(this.endDate, 'end');
    this.expenditureService.getExpenditureByDateAndBank(this.startDate, this.endDate, this.bankId).subscribe((res) => {
    this.datas = res.data;
    console.log(this.datas, 'datas')
  }  
)}}

approvedExp(){
  this.expenditureService.getApprovedExpenditure().subscribe((res) => {
    this.approved = res.data;
})
}

formatter = new Intl.NumberFormat('en-ID', {
  style: 'currency',
  currency: 'IDR',
});

total(){
  this.expenditureService.getExpenditureByDateAndBank('2020-01-01', '2022-12-31', 1).subscribe((res)=>{
    this.expenditureService.getExpenditureByDateAndBank('2020-01-01', '2022-12-31', 2).subscribe((resu)=>{
      this.expenditureService.getExpenditureByDateAndBank('2020-01-01', '2022-12-31', 3).subscribe((result)=>{
        this.expenditureService.getExpenditureByDateAndBank('2020-01-01', '2022-12-31', 4).subscribe((results)=>{
          this.mandiriNum=res;
          console.log('mandnum', this.mandiriNum)
          this.bniNum= resu;
          this.bcaNum=result;
          this.briNum=results;
          this.mandiri=this.formatter.format(res);
          this.bni=this.formatter.format(resu);
          this.bca=this.formatter.format(result);
          this.bri=this.formatter.format(results);
          this.totals=this.formatter.format(res+resu+result+results);
          console.log('totals',this.totals)
        })
      })
    })
  })
}
}
