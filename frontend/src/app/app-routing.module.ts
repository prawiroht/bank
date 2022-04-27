import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApprovalSaldoDepositoComponent } from './components/approval-saldo-deposito/approval-saldo-deposito.component';
import { ApprovalSaldoGiroComponent } from './components/approval-saldo-giro/approval-saldo-giro.component';
import { ApprovalSaldoInvestasiComponent } from './components/approval-saldo-investasi/approval-saldo-investasi.component';
import { ContainerComponent } from './components/container/container.component';
import { DashboardContainerComponent } from './components/dashboard-container/dashboard-container.component';
import { DashboardExpenditureComponent } from './components/dashboard-expenditure/dashboard-expenditure.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DepositsComponent } from './components/deposits/deposits.component';
import { ExpenditureComponent } from './components/expenditure/expenditure.component';
import { GiroComponent } from './components/giro/giro.component';
import { InvestmentComponent } from './components/investment/investment.component';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
<<<<<<< HEAD
  { path: 'user-management', component: UserManagementComponent, canActivate:[AuthGuardService] },
  { path: 'home', component: DashboardComponent, canActivate:[AuthGuardService] },
  { path: 'main', component: MainComponent },
  { path: 'expenditure', component: ExpenditureComponent, canActivate:[AuthGuardService] },
  { path: 'container', component: ContainerComponent },
  { path: 'giro', component: GiroComponent, canActivate:[AuthGuardService] },
  { path: 'deposit-approval', component: ApprovalSaldoDepositoComponent, canActivate:[AuthGuardService] },
=======
  { path: 'user-management', component: UserManagementComponent },
  { path: 'home', component: DashboardComponent, canActivate: [AuthGuardService] },
  { path: 'main', component: MainComponent, canActivate: [AuthGuardService] },
  { path: 'expenditure', component: ExpenditureComponent, canActivate: [AuthGuardService] },
  { path: 'container', component: ContainerComponent, canActivate: [AuthGuardService] },
  { path: 'giro', component: GiroComponent, canActivate: [AuthGuardService] },
  { path: 'deposits', component: DepositsComponent, canActivate: [AuthGuardService] },
  { path: 'investment', component: InvestmentComponent, canActivate: [AuthGuardService] },
  { path: 'deposit-approval', component: ApprovalSaldoDepositoComponent, canActivate: [AuthGuardService] },
>>>>>>> 016316f880b3722a2263808425aca601d23fcb85
  { path: 'giro-approval', component: ApprovalSaldoGiroComponent, },
  { path: 'investation-approval', component: ApprovalSaldoInvestasiComponent, canActivate: [AuthGuardService] },
  { path: 'dashboard-container', component: DashboardContainerComponent, canActivate: [AuthGuardService] },
  { path: 'dashboard-expenditure', component: DashboardExpenditureComponent, canActivate: [AuthGuardService] },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
