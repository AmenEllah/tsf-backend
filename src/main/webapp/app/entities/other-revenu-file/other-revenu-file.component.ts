import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOtherRevenuFile } from 'app/shared/model/other-revenu-file.model';
import { OtherRevenuFileService } from './other-revenu-file.service';
import { OtherRevenuFileDeleteDialogComponent } from './other-revenu-file-delete-dialog.component';

@Component({
  selector: 'jhi-other-revenu-file',
  templateUrl: './other-revenu-file.component.html',
})
export class OtherRevenuFileComponent implements OnInit, OnDestroy {
  otherRevenuFiles?: IOtherRevenuFile[];
  eventSubscriber?: Subscription;

  constructor(
    protected otherRevenuFileService: OtherRevenuFileService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.otherRevenuFileService.query().subscribe((res: HttpResponse<IOtherRevenuFile[]>) => (this.otherRevenuFiles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOtherRevenuFiles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOtherRevenuFile): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOtherRevenuFiles(): void {
    this.eventSubscriber = this.eventManager.subscribe('otherRevenuFileListModification', () => this.loadAll());
  }

  delete(otherRevenuFile: IOtherRevenuFile): void {
    const modalRef = this.modalService.open(OtherRevenuFileDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.otherRevenuFile = otherRevenuFile;
  }
}
