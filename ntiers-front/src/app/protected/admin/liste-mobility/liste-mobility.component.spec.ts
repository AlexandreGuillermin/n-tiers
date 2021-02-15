import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeMobilityComponent } from './liste-mobility.component';

describe('ListeMobilityComponent', () => {
  let component: ListeMobilityComponent;
  let fixture: ComponentFixture<ListeMobilityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListeMobilityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeMobilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
