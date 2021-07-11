import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TfsBackendTestModule } from '../../../test.module';
import { RequiredDocComponent } from 'app/entities/required-doc/required-doc.component';
import { RequiredDocService } from 'app/entities/required-doc/required-doc.service';
import { RequiredDoc } from 'app/shared/model/required-doc.model';

describe('Component Tests', () => {
  describe('RequiredDoc Management Component', () => {
    let comp: RequiredDocComponent;
    let fixture: ComponentFixture<RequiredDocComponent>;
    let service: RequiredDocService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [RequiredDocComponent],
      })
        .overrideTemplate(RequiredDocComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequiredDocComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequiredDocService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RequiredDoc(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.requiredDocs && comp.requiredDocs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
