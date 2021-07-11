import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { RequestUpdateComponent } from 'app/entities/request/request-update.component';
import { RequestService } from 'app/entities/request/request.service';
import { Request } from 'app/shared/model/request.model';

describe('Component Tests', () => {
  describe('Request Management Update Component', () => {
    let comp: RequestUpdateComponent;
    let fixture: ComponentFixture<RequestUpdateComponent>;
    let service: RequestService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [RequestUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RequestUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequestUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequestService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Request(123);
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
        const entity = new Request();
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
