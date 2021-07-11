import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IResidencyDocument } from 'app/shared/model/residency-document.model';
import { ResidencyDocumentService } from './residency-document.service';
import { ResidencyDocumentDeleteDialogComponent } from './residency-document-delete-dialog.component';

@Component({
  selector: 'jhi-residency-document',
  templateUrl: './residency-document.component.html',
})
export class ResidencyDocumentComponent implements OnInit, OnDestroy {
  residencyDocuments?: IResidencyDocument[];
  eventSubscriber?: Subscription;

  constructor(
    protected residencyDocumentService: ResidencyDocumentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.residencyDocumentService
      .query()
      .subscribe((res: HttpResponse<IResidencyDocument[]>) => (this.residencyDocuments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInResidencyDocuments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IResidencyDocument): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInResidencyDocuments(): void {
    this.eventSubscriber = this.eventManager.subscribe('residencyDocumentListModification', () => this.loadAll());
  }

  delete(residencyDocument: IResidencyDocument): void {
    const modalRef = this.modalService.open(ResidencyDocumentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.residencyDocument = residencyDocument;
  }
}
