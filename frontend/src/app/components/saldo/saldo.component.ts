import { Component, OnInit } from '@angular/core';
import { ChartConfiguration, ChartData, ChartType } from 'chart.js';
import { SaldoService } from 'src/app/services/saldo.service';

@Component({
  selector: 'app-saldo',
  templateUrl: './saldo.component.html',
  styleUrls: ['./saldo.component.css']
})
export class SaldoComponent implements OnInit {
  date = new Date();
  optPurchasing = [
    {label: "All", value: "All"}
  ]
  purchasing = '';
  logobni: string = '/assets/images/logobank/bni.png';
  logomandiri: string = '/assets/images/logobank/mandiri.png';

  saldo:any;
  formatter = new Intl.NumberFormat('en-ID', {
    style: 'currency',
    currency: 'IDR',
  });

  public doughnutChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: true,
        position: 'bottom',
      },
      // datalabels: {
      //   formatter: (value, ctx) => {
      //     let datasets = ctx.chart.data.datasets;
      //     let percentage = '';

      //     if (datasets.indexOf(ctx.dataset) === datasets.length - 1) {
      //         let sum:any = null;
      //         datasets.map(dataset => {
      //             sum = dataset?.data[ctx.dataIndex] != null ? dataset.data[ctx.dataIndex] : 0
      //         });
      //         percentage = Math.round((value / sum) * 100) + '%';
      //         return percentage;
      //     } else {
      //         return percentage;
      //     }
      //   },
      // },
    }
  };


  
  public doughnutChartLabels: string[] = [ 'BCA', 'BNI', 'Mandiri' ];
  public doughnutChartData: ChartData<'doughnut'> = {
    labels: this.doughnutChartLabels,
    datasets: [
      { data: [ 47, 12, 57 ] }
    ]
  };
  public doughnutChartType: ChartType = 'doughnut';

  constructor(private saldoService : SaldoService) { }

  ngOnInit(): void {
    // this.eventBusService.titlePage.next('Saldo');
    // console.log('data awal', this.jTest)
    // //get unik field buat column sebelah kiri
    // this.jTest.forEach((x: any) => {
    //   const cek = this.arr.find((i:any) => i.cdenumerator === x.cdenumerator)
    //   if(!cek){
    //     const newArr = {
    //       cdenumerator: x.cdenumerator
    //     }
    //     this.arr.push(newArr)
    //   }
    // });
    // //cari tanggal max buat loop
    // const maxval = +Math.max.apply(Math, this.jTest.map(function(o: any) { return o.tanggal; }))
    // console.log('max tanggal', maxval)
    // //ambil masing2 nilai pada tgl tertentu sesuai unik row
    // this.arr.forEach((x:any) => {
    //   for(let i=1; i<=maxval; i++){
    //     this.jTest.forEach((y:any) => {
    //       if(y.cdenumerator === x.cdenumerator && +y.tanggal === i){
    //         x['tgl'+i] = y.num
    //       }
    //     })
    //   }
    // })
    // console.log('hasil', this.arr)
    this.getSaldo();
  }

  getSaldo(){
    this.saldoService.getSaldo().subscribe((res)=>{
      this.saldo = this.formatter.format(res);
      console.log(res, 'ini resi')
    })
      
  }

}
