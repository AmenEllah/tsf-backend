import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { NationalityUpdateComponent } from 'app/entities/nationality/nationality-update.component';
import { NationalityService } from 'app/entities/nationality/nationality.service';
import { Nationality } from 'app/shared/model/nationality.model';

describe('Component Tests', () => {
  describe('Nationality Management Update Component', () => {
    let comp: NationalityUpdateComponent;
    let fixture: ComponentFixture<NationalityUpdateComponent>;
    let service: NationalityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [NationalityUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NationalityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NationalityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NationalityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Nationality(123);
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
        const entity = new Nationality();
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
