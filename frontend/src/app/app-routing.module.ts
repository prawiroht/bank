import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApprovalSaldoComponent } from './components/approval-saldo/approval-saldo.component';
import { ApprovalTransactionComponent } from './components/approval-transaction/approval-transaction.component';
import { ContainerComponent } from './components/container/container.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ExpenditureComponent } from './components/expenditure/expenditure.component';
import { GiroComponent } from './components/giro/giro.component';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { UserManagementComponent } from './components/user-management/user-management.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user-management', component: UserManagementComponent },
  { path: 'approval-saldo', component: ApprovalSaldoComponent },
  { path: 'approval-transaction', component: ApprovalTransactionComponent },
  { path: 'home', component: DashboardComponent },
  { path: 'main', component: MainComponent },
  { path: 'expenditure', component: ExpenditureComponent },
  { path: 'container', component: ContainerComponent },
  { path: 'giro', component: GiroComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
