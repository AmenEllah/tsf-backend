import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { AdressInfoUpdateComponent } from 'app/entities/adress-info/adress-info-update.component';
import { AdressInfoService } from 'app/entities/adress-info/adress-info.service';
import { AdressInfo } from 'app/shared/model/adress-info.model';

describe('Component Tests', () => {
  describe('AdressInfo Management Update Component', () => {
    let comp: AdressInfoUpdateComponent;
    let fixture: ComponentFixture<AdressInfoUpdateComponent>;
    let service: AdressInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [AdressInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AdressInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdressInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdressInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdressInfo(123);
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
        const entity = new AdressInfo();
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
