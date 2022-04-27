import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  itemDashboard: MenuItem[]= [];
  itemInput: MenuItem[]= [];
  itemApproval: MenuItem[]= [];
  itemSetting: MenuItem[]= [];

  constructor() { }

  ngOnInit(): void {
    this.itemDashboard = [
      {
          label: 'Saldo',
          icon: 'pi pi-pw pi-wallet',
          
      },
      {
          label: 'Penampungan',
          icon: 'pi pi-fw pi-tag',
      },
      {
          label: 'Utama',
          icon: 'pi pi-fw pi-star',
      },
      {
          label: 'Pengeluaran',
          icon: 'pi pi-fw pi-cog',
      }
    ];
    this.itemInput = [
      {
          label: 'Input Saldo',
          icon: 'pi pi-pw pi-file',
          items: [
            {label: 'Giro'},
            {label: 'Deposito'},
            {label: 'Investasi'}
        ]
      },
      {
          label: 'Input Transaction',
          icon: 'pi pi-fw pi-file',
          items: [
            {label: 'Penampungan'},
            {label: 'Utama'},
            {label: 'Pengeluaran'},
            {label: 'File MT940'}
        ]
      }
    ];
    this.itemApproval = [
      {
          label: 'Approval Saldo',
          icon: 'pi pi-pw pi-envelope',
          items: [
            {label: 'Giro'},
            {label: 'Deposito'},
            {label: 'Investasi'}
        ]
      },
      {
          label: 'Approval Transaction',
          icon: 'pi pi-fw pi-envelope',
          items: [
            {label: 'Penampungan'},
            {label: 'Utama'},
            {label: 'Pengeluaran'},
            {label: 'File MT940'}
        ]
      }
    ];
    this.itemSetting = [
      {
          label: 'User Access',
          icon: 'pi pi-pw pi-file',
          items: [
            {label: 'User Management'},
            {label: 'Group Management'}
        ]
      },
      {
          label: 'Parameter',
          icon: 'pi pi-fw pi-link',
          items: [
            {label: 'Bank'},
            {label: 'Sumber Dana'},
            {label: 'Jenis Penerimaan'},
            {label: 'Jenis Belanja'}
        ]
      }
    ];
}

}
