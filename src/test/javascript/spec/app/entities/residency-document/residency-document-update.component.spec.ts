import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { ResidencyDocumentUpdateComponent } from 'app/entities/residency-document/residency-document-update.component';
import { ResidencyDocumentService } from 'app/entities/residency-document/residency-document.service';
import { ResidencyDocument } from 'app/shared/model/residency-document.model';

describe('Component Tests', () => {
  describe('ResidencyDocument Management Update Component', () => {
    let comp: ResidencyDocumentUpdateComponent;
    let fixture: ComponentFixture<ResidencyDocumentUpdateComponent>;
    let service: ResidencyDocumentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [ResidencyDocumentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ResidencyDocumentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResidencyDocumentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResidencyDocumentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ResidencyDocument(123);
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
        const entity = new ResidencyDocument();
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
