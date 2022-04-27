import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalTransactionPengeluaranComponent } from './approval-transaction-pengeluaran.component';

describe('ApprovalTransactionPengeluaranComponent', () => {
  let component: ApprovalTransactionPengeluaranComponent;
  let fixture: ComponentFixture<ApprovalTransactionPengeluaranComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalTransactionPengeluaranComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalTransactionPengeluaranComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
