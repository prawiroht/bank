import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalSaldoDepositoComponent } from './approval-saldo-deposito.component';

describe('ApprovalSaldoDepositoComponent', () => {
  let component: ApprovalSaldoDepositoComponent;
  let fixture: ComponentFixture<ApprovalSaldoDepositoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalSaldoDepositoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalSaldoDepositoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
