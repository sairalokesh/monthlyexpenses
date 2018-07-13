import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthyearcategoryComponent } from './monthyearcategory.component';

describe('MonthyearcategoryComponent', () => {
  let component: MonthyearcategoryComponent;
  let fixture: ComponentFixture<MonthyearcategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonthyearcategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthyearcategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
