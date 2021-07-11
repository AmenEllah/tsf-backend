import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { MunicipalityDetailComponent } from 'app/entities/municipality/municipality-detail.component';
import { Municipality } from 'app/shared/model/municipality.model';

describe('Component Tests', () => {
  describe('Municipality Management Detail Component', () => {
    let comp: MunicipalityDetailComponent;
    let fixture: ComponentFixture<MunicipalityDetailComponent>;
    const route = ({ data: of({ municipality: new Municipality(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [MunicipalityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MunicipalityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MunicipalityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load municipality on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.municipality).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
