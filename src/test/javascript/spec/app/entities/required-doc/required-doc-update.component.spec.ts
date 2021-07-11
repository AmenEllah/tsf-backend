import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { RequiredDocUpdateComponent } from 'app/entities/required-doc/required-doc-update.component';
import { RequiredDocService } from 'app/entities/required-doc/required-doc.service';
import { RequiredDoc } from 'app/shared/model/required-doc.model';

describe('Component Tests', () => {
  describe('RequiredDoc Management Update Component', () => {
    let comp: RequiredDocUpdateComponent;
    let fixture: ComponentFixture<RequiredDocUpdateComponent>;
    let service: RequiredDocService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [RequiredDocUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RequiredDocUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequiredDocUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequiredDocService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RequiredDoc(123);
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
        const entity = new RequiredDoc();
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
