import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubcontractingStaff } from 'app/shared/model/subcontracting-staff.model';
import { SubcontractingStaffService } from './subcontracting-staff.service';

@Component({
  templateUrl: './subcontracting-staff-delete-dialog.component.html',
})
export class SubcontractingStaffDeleteDialogComponent {
  subcontractingStaff?: ISubcontractingStaff;

  constructor(
    protected subcontractingStaffService: SubcontractingStaffService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.subcontractingStaffService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subcontractingStaffListModification');
      this.activeModal.close();
    });
  }
}
