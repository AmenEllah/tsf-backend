import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { OtherRevenuFileDetailComponent } from 'app/entities/other-revenu-file/other-revenu-file-detail.component';
import { OtherRevenuFile } from 'app/shared/model/other-revenu-file.model';

describe('Component Tests', () => {
  describe('OtherRevenuFile Management Detail Component', () => {
    let comp: OtherRevenuFileDetailComponent;
    let fixture: ComponentFixture<OtherRevenuFileDetailComponent>;
    const route = ({ data: of({ otherRevenuFile: new OtherRevenuFile(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [OtherRevenuFileDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OtherRevenuFileDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OtherRevenuFileDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load otherRevenuFile on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.otherRevenuFile).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
