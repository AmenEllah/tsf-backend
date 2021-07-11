import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TfsBackendTestModule } from '../../../test.module';
import { ResidencyDocumentComponent } from 'app/entities/residency-document/residency-document.component';
import { ResidencyDocumentService } from 'app/entities/residency-document/residency-document.service';
import { ResidencyDocument } from 'app/shared/model/residency-document.model';

describe('Component Tests', () => {
  describe('ResidencyDocument Management Component', () => {
    let comp: ResidencyDocumentComponent;
    let fixture: ComponentFixture<ResidencyDocumentComponent>;
    let service: ResidencyDocumentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [ResidencyDocumentComponent],
      })
        .overrideTemplate(ResidencyDocumentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResidencyDocumentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResidencyDocumentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ResidencyDocument(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.residencyDocuments && comp.residencyDocuments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
