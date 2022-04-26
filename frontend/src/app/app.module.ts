import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AccordionModule } from 'primeng/accordion';     //accordion and accordion tab

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { GroupManagementComponent } from './components/group-management/group-management.component';
import { BankComponent } from './components/bank/bank.component';
import { FundComponent } from './components/fund/fund.component';
import { ReceivingComponent } from './components/receiving/receiving.component';
import { PurchaseComponent } from './components/purchase/purchase.component';
import { ButtonModule } from 'primeng/button';

@NgModule({
  declarations: [
    AppComponent,
    UserManagementComponent,
    GroupManagementComponent,
    BankComponent,
    FundComponent,
    ReceivingComponent,
    PurchaseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AccordionModule,
    ButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
