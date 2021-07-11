import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { DerogationUpdateComponent } from 'app/entities/derogation/derogation-update.component';
import { DerogationService } from 'app/entities/derogation/derogation.service';
import { Derogation } from 'app/shared/model/derogation.model';

describe('Component Tests', () => {
  describe('Derogation Management Update Component', () => {
    let comp: DerogationUpdateComponent;
    let fixture: ComponentFixture<DerogationUpdateComponent>;
    let service: DerogationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [DerogationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DerogationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DerogationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DerogationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Derogation(123);
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
        const entity = new Derogation();
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
