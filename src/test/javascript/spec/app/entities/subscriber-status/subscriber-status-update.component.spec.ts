import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { SubscriberStatusUpdateComponent } from 'app/entities/subscriber-status/subscriber-status-update.component';
import { SubscriberStatusService } from 'app/entities/subscriber-status/subscriber-status.service';
import { SubscriberStatus } from 'app/shared/model/subscriber-status.model';

describe('Component Tests', () => {
  describe('SubscriberStatus Management Update Component', () => {
    let comp: SubscriberStatusUpdateComponent;
    let fixture: ComponentFixture<SubscriberStatusUpdateComponent>;
    let service: SubscriberStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [SubscriberStatusUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SubscriberStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubscriberStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubscriberStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubscriberStatus(123);
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
        const entity = new SubscriberStatus();
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
