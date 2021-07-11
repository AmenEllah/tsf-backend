import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { AdresseUpdateComponent } from 'app/entities/adresse/adresse-update.component';
import { AdresseService } from 'app/entities/adresse/adresse.service';
import { Adresse } from 'app/shared/model/adresse.model';

describe('Component Tests', () => {
  describe('Adresse Management Update Component', () => {
    let comp: AdresseUpdateComponent;
    let fixture: ComponentFixture<AdresseUpdateComponent>;
    let service: AdresseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [AdresseUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AdresseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdresseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdresseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Adresse(123);
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
        const entity = new Adresse();
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
