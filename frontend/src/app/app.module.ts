import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AccordionModule } from 'primeng/accordion';     //accordion and accordion tab

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { GroupManagementComponent } from './components/group-management/group-management.component';
import { BankComponent } from './components/bank/bank.component';
import { FundComponent } from './components/fund/fund.component';
import { ReceivingComponent } from './components/receiving/receiving.component';
import { PurchaseComponent } from './components/purchase/purchase.component';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';

import { ApprovalSaldoComponent } from './components/approval-saldo/approval-saldo.component';
import { ApprovalTransactionComponent } from './components/approval-transaction/approval-transaction.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';


@NgModule({
  declarations: [
    AppComponent,
    UserManagementComponent,
    GroupManagementComponent,
    BankComponent,
    FundComponent,
    DashboardComponent,
    ReceivingComponent,
    PurchaseComponent,
    ApprovalSaldoComponent,
    ApprovalTransactionComponent,
    LoginComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AccordionModule,
    ButtonModule,
    TableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
