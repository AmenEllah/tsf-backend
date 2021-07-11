import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { StaffPermissionUpdateComponent } from 'app/entities/staff-permission/staff-permission-update.component';
import { StaffPermissionService } from 'app/entities/staff-permission/staff-permission.service';
import { StaffPermission } from 'app/shared/model/staff-permission.model';

describe('Component Tests', () => {
  describe('StaffPermission Management Update Component', () => {
    let comp: StaffPermissionUpdateComponent;
    let fixture: ComponentFixture<StaffPermissionUpdateComponent>;
    let service: StaffPermissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [StaffPermissionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StaffPermissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StaffPermissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StaffPermissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new StaffPermission(123);
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
        const entity = new StaffPermission();
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
