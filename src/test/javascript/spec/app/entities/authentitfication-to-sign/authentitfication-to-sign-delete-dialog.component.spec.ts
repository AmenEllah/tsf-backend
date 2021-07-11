import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TfsBackendTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { AuthentitficationToSignDeleteDialogComponent } from 'app/entities/authentitfication-to-sign/authentitfication-to-sign-delete-dialog.component';
import { AuthentitficationToSignService } from 'app/entities/authentitfication-to-sign/authentitfication-to-sign.service';

describe('Component Tests', () => {
  describe('AuthentitficationToSign Management Delete Component', () => {
    let comp: AuthentitficationToSignDeleteDialogComponent;
    let fixture: ComponentFixture<AuthentitficationToSignDeleteDialogComponent>;
    let service: AuthentitficationToSignService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [AuthentitficationToSignDeleteDialogComponent],
      })
        .overrideTemplate(AuthentitficationToSignDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AuthentitficationToSignDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AuthentitficationToSignService);
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
