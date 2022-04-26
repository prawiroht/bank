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
import { InputTextModule } from 'primeng/inputtext';
import { ApprovalSaldoComponent } from './components/approval-saldo/approval-saldo.component';
import { ApprovalTransactionComponent } from './components/approval-transaction/approval-transaction.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ContainerComponent } from './components/container/container.component';
import { ExpenditureComponent } from './components/expenditure/expenditure.component';
import { MainComponent } from './components/main/main.component';
import { DashboardContainerComponent } from './components/dashboard-container/dashboard-container.component';
import { DashboardExpenditureComponent } from './components/dashboard-expenditure/dashboard-expenditure.component';
import {PasswordModule} from 'primeng/password';
import {ToastModule} from 'primeng/toast';
import { FormsModule } from '@angular/forms';


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
    LoginComponent,
    ContainerComponent,
    ExpenditureComponent,
    MainComponent,
    LoginComponent,
    DashboardContainerComponent,
    DashboardExpenditureComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AccordionModule,
    ButtonModule,
    TableModule,
    InputTextModule,
    PasswordModule,
    ToastModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
