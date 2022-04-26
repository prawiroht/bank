import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalTransactionComponent } from './approval-transaction.component';

describe('ApprovalTransactionComponent', () => {
  let component: ApprovalTransactionComponent;
  let fixture: ComponentFixture<ApprovalTransactionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalTransactionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
