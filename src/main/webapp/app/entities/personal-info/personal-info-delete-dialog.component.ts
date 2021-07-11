import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonalInfo } from 'app/shared/model/personal-info.model';
import { PersonalInfoService } from './personal-info.service';

@Component({
  templateUrl: './personal-info-delete-dialog.component.html',
})
export class PersonalInfoDeleteDialogComponent {
  personalInfo?: IPersonalInfo;

  constructor(
    protected personalInfoService: PersonalInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.personalInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('personalInfoListModification');
      this.activeModal.close();
    });
  }
}
