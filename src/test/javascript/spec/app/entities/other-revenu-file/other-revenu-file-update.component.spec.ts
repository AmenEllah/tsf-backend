import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { OtherRevenuFileUpdateComponent } from 'app/entities/other-revenu-file/other-revenu-file-update.component';
import { OtherRevenuFileService } from 'app/entities/other-revenu-file/other-revenu-file.service';
import { OtherRevenuFile } from 'app/shared/model/other-revenu-file.model';

describe('Component Tests', () => {
  describe('OtherRevenuFile Management Update Component', () => {
    let comp: OtherRevenuFileUpdateComponent;
    let fixture: ComponentFixture<OtherRevenuFileUpdateComponent>;
    let service: OtherRevenuFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [OtherRevenuFileUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OtherRevenuFileUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OtherRevenuFileUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OtherRevenuFileService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OtherRevenuFile(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OtherRevenuFile();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
