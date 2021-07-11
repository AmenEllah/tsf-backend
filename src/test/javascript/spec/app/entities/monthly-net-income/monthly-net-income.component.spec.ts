import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { TfsBackendTestModule } from '../../../test.module';
import { MonthlyNetIncomeComponent } from 'app/entities/monthly-net-income/monthly-net-income.component';
import { MonthlyNetIncomeService } from 'app/entities/monthly-net-income/monthly-net-income.service';
import { MonthlyNetIncome } from 'app/shared/model/monthly-net-income.model';

describe('Component Tests', () => {
  describe('MonthlyNetIncome Management Component', () => {
    let comp: MonthlyNetIncomeComponent;
    let fixture: ComponentFixture<MonthlyNetIncomeComponent>;
    let service: MonthlyNetIncomeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [MonthlyNetIncomeComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(MonthlyNetIncomeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MonthlyNetIncomeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MonthlyNetIncomeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MonthlyNetIncome(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.monthlyNetIncomes && comp.monthlyNetIncomes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MonthlyNetIncome(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.monthlyNetIncomes && comp.monthlyNetIncomes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
