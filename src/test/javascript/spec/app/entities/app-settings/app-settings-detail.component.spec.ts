import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { AppSettingsDetailComponent } from 'app/entities/app-settings/app-settings-detail.component';
import { AppSettings } from 'app/shared/model/app-settings.model';

describe('Component Tests', () => {
  describe('AppSettings Management Detail Component', () => {
    let comp: AppSettingsDetailComponent;
    let fixture: ComponentFixture<AppSettingsDetailComponent>;
    const route = ({ data: of({ appSettings: new AppSettings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [AppSettingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AppSettingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AppSettingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load appSettings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.appSettings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
