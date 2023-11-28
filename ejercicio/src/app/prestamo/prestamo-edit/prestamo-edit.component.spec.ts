import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrestamoEditComponent } from './prestamo-edit.component';

describe('PrestamoEditComponent', () => {
  let component: PrestamoEditComponent;
  let fixture: ComponentFixture<PrestamoEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PrestamoEditComponent]
    });
    fixture = TestBed.createComponent(PrestamoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
