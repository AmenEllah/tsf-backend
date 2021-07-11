import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStaffPermission } from 'app/shared/model/staff-permission.model';
import { StaffPermissionService } from './staff-permission.service';

@Component({
  templateUrl: './staff-permission-delete-dialog.component.html',
})
export class StaffPermissionDeleteDialogComponent {
  staffPermission?: IStaffPermission;

  constructor(
    protected staffPermissionService: StaffPermissionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.staffPermissionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('staffPermissionListModification');
      this.activeModal.close();
    });
  }
}
