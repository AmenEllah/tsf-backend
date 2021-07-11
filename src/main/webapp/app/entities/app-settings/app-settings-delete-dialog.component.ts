import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAppSettings } from 'app/shared/model/app-settings.model';
import { AppSettingsService } from './app-settings.service';

@Component({
  templateUrl: './app-settings-delete-dialog.component.html',
})
export class AppSettingsDeleteDialogComponent {
  appSettings?: IAppSettings;

  constructor(
    protected appSettingsService: AppSettingsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.appSettingsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('appSettingsListModification');
      this.activeModal.close();
    });
  }
}
