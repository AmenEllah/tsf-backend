import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { SubcontractingStaffUpdateComponent } from 'app/entities/subcontracting-staff/subcontracting-staff-update.component';
import { SubcontractingStaffService } from 'app/entities/subcontracting-staff/subcontracting-staff.service';
import { SubcontractingStaff } from 'app/shared/model/subcontracting-staff.model';

describe('Component Tests', () => {
  describe('SubcontractingStaff Management Update Component', () => {
    let comp: SubcontractingStaffUpdateComponent;
    let fixture: ComponentFixture<SubcontractingStaffUpdateComponent>;
    let service: SubcontractingStaffService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [SubcontractingStaffUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SubcontractingStaffUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubcontractingStaffUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubcontractingStaffService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubcontractingStaff(123);
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
        const entity = new SubcontractingStaff();
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
