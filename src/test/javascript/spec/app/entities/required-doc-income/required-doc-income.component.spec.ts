import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TfsBackendTestModule } from '../../../test.module';
import { RequiredDocIncomeComponent } from 'app/entities/required-doc-income/required-doc-income.component';
import { RequiredDocIncomeService } from 'app/entities/required-doc-income/required-doc-income.service';
import { RequiredDocIncome } from 'app/shared/model/required-doc-income.model';

describe('Component Tests', () => {
  describe('RequiredDocIncome Management Component', () => {
    let comp: RequiredDocIncomeComponent;
    let fixture: ComponentFixture<RequiredDocIncomeComponent>;
    let service: RequiredDocIncomeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [RequiredDocIncomeComponent],
      })
        .overrideTemplate(RequiredDocIncomeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequiredDocIncomeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequiredDocIncomeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RequiredDocIncome(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.requiredDocIncomes && comp.requiredDocIncomes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
