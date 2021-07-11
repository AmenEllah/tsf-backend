import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { FinancialInfoDetailComponent } from 'app/entities/financial-info/financial-info-detail.component';
import { FinancialInfo } from 'app/shared/model/financial-info.model';

describe('Component Tests', () => {
  describe('FinancialInfo Management Detail Component', () => {
    let comp: FinancialInfoDetailComponent;
    let fixture: ComponentFixture<FinancialInfoDetailComponent>;
    const route = ({ data: of({ financialInfo: new FinancialInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [FinancialInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FinancialInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FinancialInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load financialInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.financialInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
