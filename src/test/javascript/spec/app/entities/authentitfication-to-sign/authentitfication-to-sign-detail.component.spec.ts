import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { AuthentitficationToSignDetailComponent } from 'app/entities/authentitfication-to-sign/authentitfication-to-sign-detail.component';
import { AuthentitficationToSign } from 'app/shared/model/authentitfication-to-sign.model';

describe('Component Tests', () => {
  describe('AuthentitficationToSign Management Detail Component', () => {
    let comp: AuthentitficationToSignDetailComponent;
    let fixture: ComponentFixture<AuthentitficationToSignDetailComponent>;
    const route = ({ data: of({ authentitficationToSign: new AuthentitficationToSign(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [AuthentitficationToSignDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AuthentitficationToSignDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AuthentitficationToSignDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load authentitficationToSign on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.authentitficationToSign).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
