import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoHistroyComponent } from './video-histroy.component';

describe('VideoHistroyComponent', () => {
  let component: VideoHistroyComponent;
  let fixture: ComponentFixture<VideoHistroyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VideoHistroyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VideoHistroyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
