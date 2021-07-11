import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { GovernorateDetailComponent } from 'app/entities/governorate/governorate-detail.component';
import { Governorate } from 'app/shared/model/governorate.model';

describe('Component Tests', () => {
  describe('Governorate Management Detail Component', () => {
    let comp: GovernorateDetailComponent;
    let fixture: ComponentFixture<GovernorateDetailComponent>;
    const route = ({ data: of({ governorate: new Governorate(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [GovernorateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GovernorateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GovernorateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load governorate on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.governorate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
