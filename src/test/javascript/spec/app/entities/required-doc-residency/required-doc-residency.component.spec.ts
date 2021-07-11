import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TfsBackendTestModule } from '../../../test.module';
import { RequiredDocResidencyComponent } from 'app/entities/required-doc-residency/required-doc-residency.component';
import { RequiredDocResidencyService } from 'app/entities/required-doc-residency/required-doc-residency.service';
import { RequiredDocResidency } from 'app/shared/model/required-doc-residency.model';

describe('Component Tests', () => {
  describe('RequiredDocResidency Management Component', () => {
    let comp: RequiredDocResidencyComponent;
    let fixture: ComponentFixture<RequiredDocResidencyComponent>;
    let service: RequiredDocResidencyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [RequiredDocResidencyComponent],
      })
        .overrideTemplate(RequiredDocResidencyComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequiredDocResidencyComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequiredDocResidencyService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RequiredDocResidency(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.requiredDocResidencies && comp.requiredDocResidencies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
