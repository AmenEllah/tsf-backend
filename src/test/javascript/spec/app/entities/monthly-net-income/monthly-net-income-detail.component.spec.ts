import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { MonthlyNetIncomeDetailComponent } from 'app/entities/monthly-net-income/monthly-net-income-detail.component';
import { MonthlyNetIncome } from 'app/shared/model/monthly-net-income.model';

describe('Component Tests', () => {
  describe('MonthlyNetIncome Management Detail Component', () => {
    let comp: MonthlyNetIncomeDetailComponent;
    let fixture: ComponentFixture<MonthlyNetIncomeDetailComponent>;
    const route = ({ data: of({ monthlyNetIncome: new MonthlyNetIncome(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [MonthlyNetIncomeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MonthlyNetIncomeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MonthlyNetIncomeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load monthlyNetIncome on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.monthlyNetIncome).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
