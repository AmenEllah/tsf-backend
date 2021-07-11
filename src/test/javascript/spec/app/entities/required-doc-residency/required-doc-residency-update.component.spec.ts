import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { RequiredDocResidencyUpdateComponent } from 'app/entities/required-doc-residency/required-doc-residency-update.component';
import { RequiredDocResidencyService } from 'app/entities/required-doc-residency/required-doc-residency.service';
import { RequiredDocResidency } from 'app/shared/model/required-doc-residency.model';

describe('Component Tests', () => {
  describe('RequiredDocResidency Management Update Component', () => {
    let comp: RequiredDocResidencyUpdateComponent;
    let fixture: ComponentFixture<RequiredDocResidencyUpdateComponent>;
    let service: RequiredDocResidencyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [RequiredDocResidencyUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RequiredDocResidencyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequiredDocResidencyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequiredDocResidencyService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RequiredDocResidency(123);
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
        const entity = new RequiredDocResidency();
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
