import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApprovalSaldoDepositoComponent } from './components/approval-saldo-deposito/approval-saldo-deposito.component';
import { ApprovalSaldoGiroComponent } from './components/approval-saldo-giro/approval-saldo-giro.component';
import { ApprovalSaldoInvestasiComponent } from './components/approval-saldo-investasi/approval-saldo-investasi.component';
import { ApprovalTransactionFileMt940Component } from './components/approval-transaction-file-mt940/approval-transaction-file-mt940.component';
import { ApprovalTransactionPenampunganComponent } from './components/approval-transaction-penampungan/approval-transaction-penampungan.component';
import { ApprovalTransactionPengeluaranComponent } from './components/approval-transaction-pengeluaran/approval-transaction-pengeluaran.component';
import { ApprovalTransactionUtamaComponent } from './components/approval-transaction-utama/approval-transaction-utama.component';
import { ContainerComponent } from './components/container/container.component';
import { DashboardContainerComponent } from './components/dashboard-container/dashboard-container.component';
import { DashboardExpenditureComponent } from './components/dashboard-expenditure/dashboard-expenditure.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DepositsComponent } from './components/deposits/deposits.component';
import { ExpenditureComponent } from './components/expenditure/expenditure.component';
import { GiroComponent } from './components/giro/giro.component';
import { GroupManagementComponent } from './components/group-management/group-management.component';
import { HomeComponent } from './components/home/home.component';
import { InvestmentComponent } from './components/investment/investment.component';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { SaldoComponent } from './components/saldo/saldo.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user-management', component: UserManagementComponent, canActivate:[AuthGuardService] },
  { path: 'group-management', component: GroupManagementComponent, canActivate:[AuthGuardService] },
  { path: 'home', component: HomeComponent, canActivate:[AuthGuardService] },
  { path: 'penampungan', component: ContainerComponent, canActivate:[AuthGuardService] },
  { path: 'utama', component: MainComponent, canActivate: [AuthGuardService] },
  { path: 'pengeluaran', component: ExpenditureComponent, canActivate: [AuthGuardService] },
  { path: 'giro', component: GiroComponent, canActivate: [AuthGuardService] },
  { path: 'deposits', component: DepositsComponent, canActivate: [AuthGuardService] },
  { path: 'investment', component: InvestmentComponent, canActivate: [AuthGuardService] },
  { path: 'deposit-approval', component: ApprovalSaldoDepositoComponent, canActivate: [AuthGuardService] },
  { path: 'giro-approval', component: ApprovalSaldoGiroComponent, canActivate:[AuthGuardService] },
  { path: 'investment-approval', component: ApprovalSaldoInvestasiComponent, canActivate: [AuthGuardService] },
  { path: 'main-approval', component: ApprovalTransactionUtamaComponent, canActivate: [AuthGuardService]},
  { path: 'file-mt940-approval', component: ApprovalTransactionFileMt940Component, canActivate: [AuthGuardService] },
  { path: 'container-approval', component: ApprovalTransactionPenampunganComponent, canActivate: [AuthGuardService] },
  { path: 'expenditure-approval', component: ApprovalTransactionPengeluaranComponent, canActivate: [AuthGuardService]},
  { path: 'dashboard-container', component: DashboardContainerComponent, canActivate: [AuthGuardService] },
  { path: 'dashboard-expenditure', component: DashboardExpenditureComponent, canActivate: [AuthGuardService] },
  { path: 'saldo', component: SaldoComponent, canActivate: [AuthGuardService] },

  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
