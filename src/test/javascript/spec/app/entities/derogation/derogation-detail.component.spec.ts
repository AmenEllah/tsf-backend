import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { DerogationDetailComponent } from 'app/entities/derogation/derogation-detail.component';
import { Derogation } from 'app/shared/model/derogation.model';

describe('Component Tests', () => {
  describe('Derogation Management Detail Component', () => {
    let comp: DerogationDetailComponent;
    let fixture: ComponentFixture<DerogationDetailComponent>;
    const route = ({ data: of({ derogation: new Derogation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [DerogationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DerogationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DerogationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load derogation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.derogation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
