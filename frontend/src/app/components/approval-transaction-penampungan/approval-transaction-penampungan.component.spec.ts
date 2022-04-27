import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalTransactionPenampunganComponent } from './approval-transaction-penampungan.component';

describe('ApprovalTransactionPenampunganComponent', () => {
  let component: ApprovalTransactionPenampunganComponent;
  let fixture: ComponentFixture<ApprovalTransactionPenampunganComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalTransactionPenampunganComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalTransactionPenampunganComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
