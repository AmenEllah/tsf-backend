import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { CompteDetailComponent } from 'app/entities/compte/compte-detail.component';
import { Compte } from 'app/shared/model/compte.model';

describe('Component Tests', () => {
  describe('Compte Management Detail Component', () => {
    let comp: CompteDetailComponent;
    let fixture: ComponentFixture<CompteDetailComponent>;
    const route = ({ data: of({ compte: new Compte(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [CompteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CompteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CompteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load compte on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.compte).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
