import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRequiredDoc } from 'app/shared/model/required-doc.model';
import { RequiredDocService } from './required-doc.service';
import { RequiredDocDeleteDialogComponent } from './required-doc-delete-dialog.component';

@Component({
  selector: 'jhi-required-doc',
  templateUrl: './required-doc.component.html',
})
export class RequiredDocComponent implements OnInit, OnDestroy {
  requiredDocs?: IRequiredDoc[];
  eventSubscriber?: Subscription;

  constructor(
    protected requiredDocService: RequiredDocService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.requiredDocService.query().subscribe((res: HttpResponse<IRequiredDoc[]>) => (this.requiredDocs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRequiredDocs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRequiredDoc): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRequiredDocs(): void {
    this.eventSubscriber = this.eventManager.subscribe('requiredDocListModification', () => this.loadAll());
  }

  delete(requiredDoc: IRequiredDoc): void {
    const modalRef = this.modalService.open(RequiredDocDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.requiredDoc = requiredDoc;
  }
}
