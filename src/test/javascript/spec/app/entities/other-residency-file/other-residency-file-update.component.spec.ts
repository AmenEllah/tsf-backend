import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { OtherResidencyFileUpdateComponent } from 'app/entities/other-residency-file/other-residency-file-update.component';
import { OtherResidencyFileService } from 'app/entities/other-residency-file/other-residency-file.service';
import { OtherResidencyFile } from 'app/shared/model/other-residency-file.model';

describe('Component Tests', () => {
  describe('OtherResidencyFile Management Update Component', () => {
    let comp: OtherResidencyFileUpdateComponent;
    let fixture: ComponentFixture<OtherResidencyFileUpdateComponent>;
    let service: OtherResidencyFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [OtherResidencyFileUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OtherResidencyFileUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OtherResidencyFileUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OtherResidencyFileService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OtherResidencyFile(123);
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
        const entity = new OtherResidencyFile();
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
