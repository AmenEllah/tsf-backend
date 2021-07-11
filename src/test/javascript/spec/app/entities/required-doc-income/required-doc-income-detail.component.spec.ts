import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { RequiredDocIncomeDetailComponent } from 'app/entities/required-doc-income/required-doc-income-detail.component';
import { RequiredDocIncome } from 'app/shared/model/required-doc-income.model';

describe('Component Tests', () => {
  describe('RequiredDocIncome Management Detail Component', () => {
    let comp: RequiredDocIncomeDetailComponent;
    let fixture: ComponentFixture<RequiredDocIncomeDetailComponent>;
    const route = ({ data: of({ requiredDocIncome: new RequiredDocIncome(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [RequiredDocIncomeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RequiredDocIncomeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RequiredDocIncomeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load requiredDocIncome on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.requiredDocIncome).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
