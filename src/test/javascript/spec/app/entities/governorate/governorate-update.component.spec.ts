import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { GovernorateUpdateComponent } from 'app/entities/governorate/governorate-update.component';
import { GovernorateService } from 'app/entities/governorate/governorate.service';
import { Governorate } from 'app/shared/model/governorate.model';

describe('Component Tests', () => {
  describe('Governorate Management Update Component', () => {
    let comp: GovernorateUpdateComponent;
    let fixture: ComponentFixture<GovernorateUpdateComponent>;
    let service: GovernorateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [GovernorateUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GovernorateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GovernorateUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GovernorateService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Governorate(123);
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
        const entity = new Governorate();
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
