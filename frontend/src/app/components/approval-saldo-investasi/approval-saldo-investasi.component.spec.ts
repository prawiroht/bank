import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalSaldoInvestasiComponent } from './approval-saldo-investasi.component';

describe('ApprovalSaldoInvestasiComponent', () => {
  let component: ApprovalSaldoInvestasiComponent;
  let fixture: ComponentFixture<ApprovalSaldoInvestasiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalSaldoInvestasiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalSaldoInvestasiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
