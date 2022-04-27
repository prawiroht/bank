import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalTransactionFileMt940Component } from './approval-transaction-file-mt940.component';

describe('ApprovalTransactionFileMt940Component', () => {
  let component: ApprovalTransactionFileMt940Component;
  let fixture: ComponentFixture<ApprovalTransactionFileMt940Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalTransactionFileMt940Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalTransactionFileMt940Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
