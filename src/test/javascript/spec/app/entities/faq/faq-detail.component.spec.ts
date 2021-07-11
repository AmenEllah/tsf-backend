import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { FAQDetailComponent } from 'app/entities/faq/faq-detail.component';
import { FAQ } from 'app/shared/model/faq.model';

describe('Component Tests', () => {
  describe('FAQ Management Detail Component', () => {
    let comp: FAQDetailComponent;
    let fixture: ComponentFixture<FAQDetailComponent>;
    const route = ({ data: of({ fAQ: new FAQ(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [FAQDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FAQDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FAQDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fAQ on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fAQ).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
