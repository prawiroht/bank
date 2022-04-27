import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApprovalSaldoComponent } from './components/approval-saldo/approval-saldo.component';
import { ApprovalTransactionComponent } from './components/approval-transaction/approval-transaction.component';
import { ContainerComponent } from './components/container/container.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ExpenditureComponent } from './components/expenditure/expenditure.component';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: DashboardComponent, canActivate:[AuthGuardService] },
  { path: 'user-management', component: UserManagementComponent, canActivate:[AuthGuardService] },
  { path: 'approval-saldo', component: ApprovalSaldoComponent, canActivate:[AuthGuardService] },
  { path: 'approval-transaction', component: ApprovalTransactionComponent, canActivate:[AuthGuardService] },
  { path: 'main', component: MainComponent, canActivate:[AuthGuardService] },
  { path: 'expenditure', component: ExpenditureComponent, canActivate:[AuthGuardService] },
  { path: 'container', component: ContainerComponent, canActivate:[AuthGuardService] },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
