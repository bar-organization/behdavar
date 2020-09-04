import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinancialStatusComponent } from './financial-status.component';

describe('FinancialStatusComponent', () => {
  let component: FinancialStatusComponent;
  let fixture: ComponentFixture<FinancialStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinancialStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinancialStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
