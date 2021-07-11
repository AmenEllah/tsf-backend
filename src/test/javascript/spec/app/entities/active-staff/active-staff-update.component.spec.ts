import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { ActiveStaffUpdateComponent } from 'app/entities/active-staff/active-staff-update.component';
import { ActiveStaffService } from 'app/entities/active-staff/active-staff.service';
import { ActiveStaff } from 'app/shared/model/active-staff.model';

describe('Component Tests', () => {
  describe('ActiveStaff Management Update Component', () => {
    let comp: ActiveStaffUpdateComponent;
    let fixture: ComponentFixture<ActiveStaffUpdateComponent>;
    let service: ActiveStaffService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [ActiveStaffUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ActiveStaffUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ActiveStaffUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ActiveStaffService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ActiveStaff(123);
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
        const entity = new ActiveStaff();
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
