import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GiroComponent } from './giro.component';

describe('GiroComponent', () => {
  let component: GiroComponent;
  let fixture: ComponentFixture<GiroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GiroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GiroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
