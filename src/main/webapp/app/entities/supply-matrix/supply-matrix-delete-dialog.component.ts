import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISupplyMatrix } from 'app/shared/model/supply-matrix.model';
import { SupplyMatrixService } from './supply-matrix.service';

@Component({
  templateUrl: './supply-matrix-delete-dialog.component.html',
})
export class SupplyMatrixDeleteDialogComponent {
  supplyMatrix?: ISupplyMatrix;

  constructor(
    protected supplyMatrixService: SupplyMatrixService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.supplyMatrixService.delete(id).subscribe(() => {
      this.eventManager.broadcast('supplyMatrixListModification');
      this.activeModal.close();
    });
  }
}
