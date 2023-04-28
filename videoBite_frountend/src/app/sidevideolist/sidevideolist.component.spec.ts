import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidevideolistComponent } from './sidevideolist.component';

describe('SidevideolistComponent', () => {
  let component: SidevideolistComponent;
  let fixture: ComponentFixture<SidevideolistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidevideolistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidevideolistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
