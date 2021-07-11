import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { ReqDocUploadDetailComponent } from 'app/entities/req-doc-upload/req-doc-upload-detail.component';
import { ReqDocUpload } from 'app/shared/model/req-doc-upload.model';

describe('Component Tests', () => {
  describe('ReqDocUpload Management Detail Component', () => {
    let comp: ReqDocUploadDetailComponent;
    let fixture: ComponentFixture<ReqDocUploadDetailComponent>;
    const route = ({ data: of({ reqDocUpload: new ReqDocUpload(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [ReqDocUploadDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ReqDocUploadDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReqDocUploadDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load reqDocUpload on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reqDocUpload).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
