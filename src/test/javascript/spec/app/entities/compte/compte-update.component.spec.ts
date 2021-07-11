import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { CompteUpdateComponent } from 'app/entities/compte/compte-update.component';
import { CompteService } from 'app/entities/compte/compte.service';
import { Compte } from 'app/shared/model/compte.model';

describe('Component Tests', () => {
  describe('Compte Management Update Component', () => {
    let comp: CompteUpdateComponent;
    let fixture: ComponentFixture<CompteUpdateComponent>;
    let service: CompteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [CompteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CompteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Compte(123);
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
        const entity = new Compte();
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
