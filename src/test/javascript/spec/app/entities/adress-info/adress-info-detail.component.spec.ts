import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { AdressInfoDetailComponent } from 'app/entities/adress-info/adress-info-detail.component';
import { AdressInfo } from 'app/shared/model/adress-info.model';

describe('Component Tests', () => {
  describe('AdressInfo Management Detail Component', () => {
    let comp: AdressInfoDetailComponent;
    let fixture: ComponentFixture<AdressInfoDetailComponent>;
    const route = ({ data: of({ adressInfo: new AdressInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [AdressInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AdressInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdressInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load adressInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.adressInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
