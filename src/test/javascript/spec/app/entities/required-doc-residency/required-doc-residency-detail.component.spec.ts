import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { RequiredDocResidencyDetailComponent } from 'app/entities/required-doc-residency/required-doc-residency-detail.component';
import { RequiredDocResidency } from 'app/shared/model/required-doc-residency.model';

describe('Component Tests', () => {
  describe('RequiredDocResidency Management Detail Component', () => {
    let comp: RequiredDocResidencyDetailComponent;
    let fixture: ComponentFixture<RequiredDocResidencyDetailComponent>;
    const route = ({ data: of({ requiredDocResidency: new RequiredDocResidency(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [RequiredDocResidencyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RequiredDocResidencyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RequiredDocResidencyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load requiredDocResidency on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.requiredDocResidency).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
