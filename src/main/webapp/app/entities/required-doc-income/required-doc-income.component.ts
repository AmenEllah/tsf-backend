import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRequiredDocIncome } from 'app/shared/model/required-doc-income.model';
import { RequiredDocIncomeService } from './required-doc-income.service';
import { RequiredDocIncomeDeleteDialogComponent } from './required-doc-income-delete-dialog.component';

@Component({
  selector: 'jhi-required-doc-income',
  templateUrl: './required-doc-income.component.html',
})
export class RequiredDocIncomeComponent implements OnInit, OnDestroy {
  requiredDocIncomes?: IRequiredDocIncome[];
  eventSubscriber?: Subscription;

  constructor(
    protected requiredDocIncomeService: RequiredDocIncomeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.requiredDocIncomeService
      .query()
      .subscribe((res: HttpResponse<IRequiredDocIncome[]>) => (this.requiredDocIncomes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRequiredDocIncomes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRequiredDocIncome): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRequiredDocIncomes(): void {
    this.eventSubscriber = this.eventManager.subscribe('requiredDocIncomeListModification', () => this.loadAll());
  }

  delete(requiredDocIncome: IRequiredDocIncome): void {
    const modalRef = this.modalService.open(RequiredDocIncomeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.requiredDocIncome = requiredDocIncome;
  }
}
