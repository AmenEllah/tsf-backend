import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TfsBackendTestModule } from '../../../test.module';
import { OtherRevenuFileComponent } from 'app/entities/other-revenu-file/other-revenu-file.component';
import { OtherRevenuFileService } from 'app/entities/other-revenu-file/other-revenu-file.service';
import { OtherRevenuFile } from 'app/shared/model/other-revenu-file.model';

describe('Component Tests', () => {
  describe('OtherRevenuFile Management Component', () => {
    let comp: OtherRevenuFileComponent;
    let fixture: ComponentFixture<OtherRevenuFileComponent>;
    let service: OtherRevenuFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [OtherRevenuFileComponent],
      })
        .overrideTemplate(OtherRevenuFileComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OtherRevenuFileComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OtherRevenuFileService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OtherRevenuFile(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.otherRevenuFiles && comp.otherRevenuFiles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
