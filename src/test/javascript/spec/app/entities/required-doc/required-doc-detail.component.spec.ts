import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { RequiredDocDetailComponent } from 'app/entities/required-doc/required-doc-detail.component';
import { RequiredDoc } from 'app/shared/model/required-doc.model';

describe('Component Tests', () => {
  describe('RequiredDoc Management Detail Component', () => {
    let comp: RequiredDocDetailComponent;
    let fixture: ComponentFixture<RequiredDocDetailComponent>;
    const route = ({ data: of({ requiredDoc: new RequiredDoc(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [RequiredDocDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RequiredDocDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RequiredDocDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load requiredDoc on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.requiredDoc).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
