import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActiveStaff } from 'app/shared/model/active-staff.model';
import { ActiveStaffService } from './active-staff.service';

@Component({
  templateUrl: './active-staff-delete-dialog.component.html',
})
export class ActiveStaffDeleteDialogComponent {
  activeStaff?: IActiveStaff;

  constructor(
    protected activeStaffService: ActiveStaffService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.activeStaffService.delete(id).subscribe(() => {
      this.eventManager.broadcast('activeStaffListModification');
      this.activeModal.close();
    });
  }
}
