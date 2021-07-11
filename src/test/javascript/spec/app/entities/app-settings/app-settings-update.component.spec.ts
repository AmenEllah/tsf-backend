import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { AppSettingsUpdateComponent } from 'app/entities/app-settings/app-settings-update.component';
import { AppSettingsService } from 'app/entities/app-settings/app-settings.service';
import { AppSettings } from 'app/shared/model/app-settings.model';

describe('Component Tests', () => {
  describe('AppSettings Management Update Component', () => {
    let comp: AppSettingsUpdateComponent;
    let fixture: ComponentFixture<AppSettingsUpdateComponent>;
    let service: AppSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [AppSettingsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AppSettingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AppSettingsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AppSettingsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AppSettings(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AppSettings();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
