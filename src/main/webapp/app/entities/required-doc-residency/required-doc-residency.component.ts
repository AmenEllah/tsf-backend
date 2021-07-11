import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRequiredDocResidency } from 'app/shared/model/required-doc-residency.model';
import { RequiredDocResidencyService } from './required-doc-residency.service';
import { RequiredDocResidencyDeleteDialogComponent } from './required-doc-residency-delete-dialog.component';

@Component({
  selector: 'jhi-required-doc-residency',
  templateUrl: './required-doc-residency.component.html',
})
export class RequiredDocResidencyComponent implements OnInit, OnDestroy {
  requiredDocResidencies?: IRequiredDocResidency[];
  eventSubscriber?: Subscription;

  constructor(
    protected requiredDocResidencyService: RequiredDocResidencyService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.requiredDocResidencyService
      .query()
      .subscribe((res: HttpResponse<IRequiredDocResidency[]>) => (this.requiredDocResidencies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRequiredDocResidencies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRequiredDocResidency): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRequiredDocResidencies(): void {
    this.eventSubscriber = this.eventManager.subscribe('requiredDocResidencyListModification', () => this.loadAll());
  }

  delete(requiredDocResidency: IRequiredDocResidency): void {
    const modalRef = this.modalService.open(RequiredDocResidencyDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.requiredDocResidency = requiredDocResidency;
  }
}
