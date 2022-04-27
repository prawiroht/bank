import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApprovalSaldoDepositoComponent } from './components/approval-saldo-deposito/approval-saldo-deposito.component';
import { ApprovalSaldoGiroComponent } from './components/approval-saldo-giro/approval-saldo-giro.component';
import { ApprovalSaldoInvestasiComponent } from './components/approval-saldo-investasi/approval-saldo-investasi.component';
import { ContainerComponent } from './components/container/container.component';
import { DashboardContainerComponent } from './components/dashboard-container/dashboard-container.component';
import { DashboardExpenditureComponent } from './components/dashboard-expenditure/dashboard-expenditure.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ExpenditureComponent } from './components/expenditure/expenditure.component';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { UserManagementComponent } from './components/user-management/user-management.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user-management', component: UserManagementComponent },
  { path: 'home', component: DashboardComponent },
  { path: 'main', component: MainComponent },
  { path: 'expenditure', component: ExpenditureComponent },
  { path: 'container', component: ContainerComponent },
  { path: 'deposit-approval', component: ApprovalSaldoDepositoComponent },
  { path: 'giro-approval', component: ApprovalSaldoGiroComponent },
  { path: 'investation-approval', component: ApprovalSaldoInvestasiComponent },
  { path: 'dashboard-container', component: DashboardContainerComponent},
  { path: 'dashboard-expenditure', component: DashboardExpenditureComponent},
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
