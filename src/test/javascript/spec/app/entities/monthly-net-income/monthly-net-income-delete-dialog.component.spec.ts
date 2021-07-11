import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TfsBackendTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { MonthlyNetIncomeDeleteDialogComponent } from 'app/entities/monthly-net-income/monthly-net-income-delete-dialog.component';
import { MonthlyNetIncomeService } from 'app/entities/monthly-net-income/monthly-net-income.service';

describe('Component Tests', () => {
  describe('MonthlyNetIncome Management Delete Component', () => {
    let comp: MonthlyNetIncomeDeleteDialogComponent;
    let fixture: ComponentFixture<MonthlyNetIncomeDeleteDialogComponent>;
    let service: MonthlyNetIncomeService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [MonthlyNetIncomeDeleteDialogComponent],
      })
        .overrideTemplate(MonthlyNetIncomeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MonthlyNetIncomeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MonthlyNetIncomeService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
