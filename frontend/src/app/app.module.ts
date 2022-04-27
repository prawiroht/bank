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
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ContainerComponent } from './components/container/container.component';
import { ExpenditureComponent } from './components/expenditure/expenditure.component';
import { MainComponent } from './components/main/main.component';
import { DashboardContainerComponent } from './components/dashboard-container/dashboard-container.component';
import { DashboardExpenditureComponent } from './components/dashboard-expenditure/dashboard-expenditure.component';
import { PasswordModule } from 'primeng/password';
import { ToastModule } from 'primeng/toast';
import { CheckboxModule } from 'primeng/checkbox';
import { FormsModule } from '@angular/forms';
import { GiroComponent } from './components/giro/giro.component';
import { DepositsComponent } from './components/deposits/deposits.component';
import { InvestmentComponent } from './components/investment/investment.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ApprovalSaldoGiroComponent } from './components/approval-saldo-giro/approval-saldo-giro.component';
import { ApprovalSaldoDepositoComponent } from './components/approval-saldo-deposito/approval-saldo-deposito.component';
import { ApprovalSaldoInvestasiComponent } from './components/approval-saldo-investasi/approval-saldo-investasi.component';
import { ApprovalTransactionPenampunganComponent } from './components/approval-transaction-penampungan/approval-transaction-penampungan.component';
import { ApprovalTransactionUtamaComponent } from './components/approval-transaction-utama/approval-transaction-utama.component';
import { ApprovalTransactionPengeluaranComponent } from './components/approval-transaction-pengeluaran/approval-transaction-pengeluaran.component';
import { ApprovalTransactionFileMt940Component } from './components/approval-transaction-file-mt940/approval-transaction-file-mt940.component';
import { FieldsetModule } from 'primeng/fieldset';

import { CalendarModule } from 'primeng/calendar';
import { CardModule } from 'primeng/card';
import { ChartModule } from 'primeng/chart';
import { PanelMenuModule } from 'primeng/panelmenu';
import { MenuItem } from 'primeng/api';
import { PanelModule } from 'primeng/panel';


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
    LoginComponent,
    ContainerComponent,
    ExpenditureComponent,
    MainComponent,
    LoginComponent,
    DashboardContainerComponent,
    DashboardExpenditureComponent,
    GiroComponent,
    DepositsComponent,
    InvestmentComponent,
    ApprovalSaldoGiroComponent,
    ApprovalSaldoDepositoComponent,
    ApprovalSaldoInvestasiComponent,
    ApprovalTransactionPenampunganComponent,
    ApprovalTransactionUtamaComponent,
    ApprovalTransactionPengeluaranComponent,
    ApprovalTransactionFileMt940Component
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
    FormsModule,
    HttpClientModule,
    CheckboxModule,
    MessagesModule,
    MessageModule,
    DialogModule,
    BrowserAnimationsModule,
    FieldsetModule,
    CalendarModule,
    CardModule,
    ChartModule,
    PanelMenuModule,
    PanelModule,
    BrowserAnimationsModule,
    AccordionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
