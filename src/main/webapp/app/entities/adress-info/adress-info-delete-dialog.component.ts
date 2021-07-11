import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdressInfo } from 'app/shared/model/adress-info.model';
import { AdressInfoService } from './adress-info.service';

@Component({
  templateUrl: './adress-info-delete-dialog.component.html',
})
export class AdressInfoDeleteDialogComponent {
  adressInfo?: IAdressInfo;

  constructor(
    protected adressInfoService: AdressInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.adressInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('adressInfoListModification');
      this.activeModal.close();
    });
  }
}
