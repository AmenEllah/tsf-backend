import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { FAQUpdateComponent } from 'app/entities/faq/faq-update.component';
import { FAQService } from 'app/entities/faq/faq.service';
import { FAQ } from 'app/shared/model/faq.model';

describe('Component Tests', () => {
  describe('FAQ Management Update Component', () => {
    let comp: FAQUpdateComponent;
    let fixture: ComponentFixture<FAQUpdateComponent>;
    let service: FAQService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [FAQUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FAQUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FAQUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FAQService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FAQ(123);
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
        const entity = new FAQ();
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
