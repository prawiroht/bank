import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ApprovalSaldoComponent } from './components/approval-saldo/approval-saldo.component';
import { ApprovalTransactionComponent } from './components/approval-transaction/approval-transaction.component';

@NgModule({
  declarations: [
    AppComponent,
    ApprovalSaldoComponent,
    ApprovalTransactionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
