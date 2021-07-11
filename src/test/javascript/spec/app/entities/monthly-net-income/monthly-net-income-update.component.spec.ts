import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { MonthlyNetIncomeUpdateComponent } from 'app/entities/monthly-net-income/monthly-net-income-update.component';
import { MonthlyNetIncomeService } from 'app/entities/monthly-net-income/monthly-net-income.service';
import { MonthlyNetIncome } from 'app/shared/model/monthly-net-income.model';

describe('Component Tests', () => {
  describe('MonthlyNetIncome Management Update Component', () => {
    let comp: MonthlyNetIncomeUpdateComponent;
    let fixture: ComponentFixture<MonthlyNetIncomeUpdateComponent>;
    let service: MonthlyNetIncomeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [MonthlyNetIncomeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MonthlyNetIncomeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MonthlyNetIncomeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MonthlyNetIncomeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MonthlyNetIncome(123);
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
        const entity = new MonthlyNetIncome();
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
