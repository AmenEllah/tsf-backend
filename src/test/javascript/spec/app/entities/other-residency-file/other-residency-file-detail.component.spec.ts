import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { OtherResidencyFileDetailComponent } from 'app/entities/other-residency-file/other-residency-file-detail.component';
import { OtherResidencyFile } from 'app/shared/model/other-residency-file.model';

describe('Component Tests', () => {
  describe('OtherResidencyFile Management Detail Component', () => {
    let comp: OtherResidencyFileDetailComponent;
    let fixture: ComponentFixture<OtherResidencyFileDetailComponent>;
    const route = ({ data: of({ otherResidencyFile: new OtherResidencyFile(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [OtherResidencyFileDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OtherResidencyFileDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OtherResidencyFileDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load otherResidencyFile on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.otherResidencyFile).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
