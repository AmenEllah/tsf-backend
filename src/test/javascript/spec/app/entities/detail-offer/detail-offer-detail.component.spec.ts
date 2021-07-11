import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { DetailOfferDetailComponent } from 'app/entities/detail-offer/detail-offer-detail.component';
import { DetailOffer } from 'app/shared/model/detail-offer.model';

describe('Component Tests', () => {
  describe('DetailOffer Management Detail Component', () => {
    let comp: DetailOfferDetailComponent;
    let fixture: ComponentFixture<DetailOfferDetailComponent>;
    const route = ({ data: of({ detailOffer: new DetailOffer(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [DetailOfferDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DetailOfferDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DetailOfferDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load detailOffer on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.detailOffer).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
