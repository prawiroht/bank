import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalSaldoComponent } from './approval-saldo.component';

describe('ApprovalSaldoComponent', () => {
  let component: ApprovalSaldoComponent;
  let fixture: ComponentFixture<ApprovalSaldoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalSaldoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalSaldoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
