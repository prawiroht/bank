import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardExpenditureComponent } from './dashboard-expenditure.component';

describe('DashboardExpenditureComponent', () => {
  let component: DashboardExpenditureComponent;
  let fixture: ComponentFixture<DashboardExpenditureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardExpenditureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardExpenditureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
