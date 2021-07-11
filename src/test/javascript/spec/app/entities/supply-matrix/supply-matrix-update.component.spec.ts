import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { SupplyMatrixUpdateComponent } from 'app/entities/supply-matrix/supply-matrix-update.component';
import { SupplyMatrixService } from 'app/entities/supply-matrix/supply-matrix.service';
import { SupplyMatrix } from 'app/shared/model/supply-matrix.model';

describe('Component Tests', () => {
  describe('SupplyMatrix Management Update Component', () => {
    let comp: SupplyMatrixUpdateComponent;
    let fixture: ComponentFixture<SupplyMatrixUpdateComponent>;
    let service: SupplyMatrixService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [SupplyMatrixUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SupplyMatrixUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SupplyMatrixUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SupplyMatrixService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SupplyMatrix(123);
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
        const entity = new SupplyMatrix();
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
