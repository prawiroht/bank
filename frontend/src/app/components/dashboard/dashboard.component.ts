import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { UserService } from 'src/app/services/user.service';
import { MenuItem } from 'primeng/api';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  itemDashboard: MenuItem[] = [];
  itemInput: MenuItem[] = [];
  itemApproval: MenuItem[] = [];
  itemSetting: MenuItem[] = [];
  itemHome: MenuItem[] = [];

  constructor(private messageService: MessageService, private userService: UserService) { }
  logout(): void {
    this.messageService.add({ key: 'tc', severity: 'info', summary: 'Goodbye', detail: 'Thank you, see you later' });
    localStorage.clear();
    window.location.reload()
  }

  ngOnInit(): void {

    this.itemHome = [
      {
        label: 'Dashboard',
        icon: 'pi pi-pw pi-home',
        routerLink: '/home'
      }
    ]
    this.itemDashboard = [
      {
        label: 'Saldo',
        icon: 'pi pi-pw pi-wallet',
        routerLink: '/saldo'

      },
      {
        label: 'Penampungan',
        icon: 'pi pi-fw pi-tag',
        routerLink: '/dashboard-container'
        
      },
      {
        label: 'Utama',
        icon: 'pi pi-fw pi-star',
        
      },
      {
        label: 'Pengeluaran',
        icon: 'pi pi-fw pi-cog',
        routerLink: '/dashboard-expenditure'
      }
    ];
    this.itemInput = [
      {
        label: 'Input Saldo',
        icon: 'pi pi-pw pi-file',
        items: [
          { label: 'Giro', routerLink: '/giro' },
          { label: 'Deposito', routerLink: '/deposits' },
          { label: 'Investasi', routerLink: '/investment' }
        ]
      },
      {
        label: 'Input Transaction',
        icon: 'pi pi-fw pi-file',
        items: [
          { label: 'Penampungan', routerLink: '/penampungan' },
          { label: 'Utama', routerLink: '/utama' },
          { label: 'Pengeluaran', routerLink: '/pengeluaran' },
          { label: 'File MT940' }
        ]
      }
    ];
    this.itemApproval = [
      {
        label: 'Approval Saldo',
        icon: 'pi pi-pw pi-envelope',
        items: [
          { label: 'Giro',  routerLink: '/giro-approval'},
          { label: 'Deposito',  routerLink: '/deposit-approval'},
          { label: 'Investasi', routerLink: '/investment-approval'}
        ]
      },
      {
        label: 'Approval Transaction',
        icon: 'pi pi-fw pi-envelope',
        items: [
          { label: 'Penampungan', routerLink: '/container-approval' },
          { label: 'Utama',  routerLink: '/main-approval'},
          { label: 'Pengeluaran', routerLink: '/expenditure-approval' },
          { label: 'File MT940', routerLink: '/file-mt940-approval'}
        ]
      }
    ];
    this.itemSetting = [
      {
        label: 'User Access',
        icon: 'pi pi-pw pi-file',
        items: [
          {
            label: 'User Management',
            routerLink: '/user-management'
          },
          {
            label: 'Group Management',
            routerLink: '/group-management'
          }
        ]
      },
      {
        label: 'Parameter',
        icon: 'pi pi-fw pi-link',
        items: [
          { label: 'Bank' },
          { label: 'Sumber Dana' },
          { label: 'Jenis Penerimaan' },
          { label: 'Jenis Belanja' },
        ],
      },
      {
        label: 'Logout',
        icon: 'pi pi-power-off',
        command: () => this.logout()

      }
    ];
  }

}
