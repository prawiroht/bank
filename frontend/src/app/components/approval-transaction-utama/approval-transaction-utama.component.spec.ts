import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalTransactionUtamaComponent } from './approval-transaction-utama.component';

describe('ApprovalTransactionUtamaComponent', () => {
  let component: ApprovalTransactionUtamaComponent;
  let fixture: ComponentFixture<ApprovalTransactionUtamaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalTransactionUtamaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalTransactionUtamaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
