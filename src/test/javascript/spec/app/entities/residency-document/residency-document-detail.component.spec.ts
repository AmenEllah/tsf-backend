import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { ResidencyDocumentDetailComponent } from 'app/entities/residency-document/residency-document-detail.component';
import { ResidencyDocument } from 'app/shared/model/residency-document.model';

describe('Component Tests', () => {
  describe('ResidencyDocument Management Detail Component', () => {
    let comp: ResidencyDocumentDetailComponent;
    let fixture: ComponentFixture<ResidencyDocumentDetailComponent>;
    const route = ({ data: of({ residencyDocument: new ResidencyDocument(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [ResidencyDocumentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ResidencyDocumentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ResidencyDocumentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load residencyDocument on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.residencyDocument).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
