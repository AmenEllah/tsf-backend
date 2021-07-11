import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOtherResidencyFile } from 'app/shared/model/other-residency-file.model';
import { OtherResidencyFileService } from './other-residency-file.service';
import { OtherResidencyFileDeleteDialogComponent } from './other-residency-file-delete-dialog.component';

@Component({
  selector: 'jhi-other-residency-file',
  templateUrl: './other-residency-file.component.html',
})
export class OtherResidencyFileComponent implements OnInit, OnDestroy {
  otherResidencyFiles?: IOtherResidencyFile[];
  eventSubscriber?: Subscription;

  constructor(
    protected otherResidencyFileService: OtherResidencyFileService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.otherResidencyFileService
      .query()
      .subscribe((res: HttpResponse<IOtherResidencyFile[]>) => (this.otherResidencyFiles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOtherResidencyFiles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOtherResidencyFile): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOtherResidencyFiles(): void {
    this.eventSubscriber = this.eventManager.subscribe('otherResidencyFileListModification', () => this.loadAll());
  }

  delete(otherResidencyFile: IOtherResidencyFile): void {
    const modalRef = this.modalService.open(OtherResidencyFileDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.otherResidencyFile = otherResidencyFile;
  }
}
