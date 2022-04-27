import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalSaldoGiroComponent } from './approval-saldo-giro.component';

describe('ApprovalSaldoGiroComponent', () => {
  let component: ApprovalSaldoGiroComponent;
  let fixture: ComponentFixture<ApprovalSaldoGiroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalSaldoGiroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalSaldoGiroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
