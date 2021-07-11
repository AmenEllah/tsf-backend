import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { ReqDocUploadUpdateComponent } from 'app/entities/req-doc-upload/req-doc-upload-update.component';
import { ReqDocUploadService } from 'app/entities/req-doc-upload/req-doc-upload.service';
import { ReqDocUpload } from 'app/shared/model/req-doc-upload.model';

describe('Component Tests', () => {
  describe('ReqDocUpload Management Update Component', () => {
    let comp: ReqDocUploadUpdateComponent;
    let fixture: ComponentFixture<ReqDocUploadUpdateComponent>;
    let service: ReqDocUploadService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [ReqDocUploadUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ReqDocUploadUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReqDocUploadUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReqDocUploadService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReqDocUpload(123);
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
        const entity = new ReqDocUpload();
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
