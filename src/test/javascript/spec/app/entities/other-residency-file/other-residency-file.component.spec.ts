import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TfsBackendTestModule } from '../../../test.module';
import { OtherResidencyFileComponent } from 'app/entities/other-residency-file/other-residency-file.component';
import { OtherResidencyFileService } from 'app/entities/other-residency-file/other-residency-file.service';
import { OtherResidencyFile } from 'app/shared/model/other-residency-file.model';

describe('Component Tests', () => {
  describe('OtherResidencyFile Management Component', () => {
    let comp: OtherResidencyFileComponent;
    let fixture: ComponentFixture<OtherResidencyFileComponent>;
    let service: OtherResidencyFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [OtherResidencyFileComponent],
      })
        .overrideTemplate(OtherResidencyFileComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OtherResidencyFileComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OtherResidencyFileService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OtherResidencyFile(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.otherResidencyFiles && comp.otherResidencyFiles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
