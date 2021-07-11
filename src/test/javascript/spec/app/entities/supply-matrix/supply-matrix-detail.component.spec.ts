import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { SupplyMatrixDetailComponent } from 'app/entities/supply-matrix/supply-matrix-detail.component';
import { SupplyMatrix } from 'app/shared/model/supply-matrix.model';

describe('Component Tests', () => {
  describe('SupplyMatrix Management Detail Component', () => {
    let comp: SupplyMatrixDetailComponent;
    let fixture: ComponentFixture<SupplyMatrixDetailComponent>;
    const route = ({ data: of({ supplyMatrix: new SupplyMatrix(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [SupplyMatrixDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SupplyMatrixDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SupplyMatrixDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load supplyMatrix on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.supplyMatrix).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
