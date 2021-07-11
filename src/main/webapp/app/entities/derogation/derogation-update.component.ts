import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDerogation, Derogation } from 'app/shared/model/derogation.model';
import { DerogationService } from './derogation.service';
import { IRequest } from 'app/shared/model/request.model';
import { RequestService } from 'app/entities/request/request.service';

@Component({
  selector: 'jhi-derogation-update',
  templateUrl: './derogation-update.component.html',
})
export class DerogationUpdateComponent implements OnInit {
  isSaving = false;
  requests: IRequest[] = [];

  editForm = this.fb.group({
    id: [],
    message: [],
    affectation: [],
    matricule: [],
    requestId: [],
  });

  constructor(
    protected derogationService: DerogationService,
    protected requestService: RequestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ derogation }) => {
      this.updateForm(derogation);

      this.requestService.query().subscribe((res: HttpResponse<IRequest[]>) => (this.requests = res.body || []));
    });
  }

  updateForm(derogation: IDerogation): void {
    this.editForm.patchValue({
      id: derogation.id,
      message: derogation.message,
      affectation: derogation.affectation,
      matricule: derogation.matricule,
      requestId: derogation.requestId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const derogation = this.createFromForm();
    if (derogation.id !== undefined) {
      this.subscribeToSaveResponse(this.derogationService.update(derogation));
    } else {
      this.subscribeToSaveResponse(this.derogationService.create(derogation));
    }
  }

  private createFromForm(): IDerogation {
    return {
      ...new Derogation(),
      id: this.editForm.get(['id'])!.value,
      message: this.editForm.get(['message'])!.value,
      affectation: this.editForm.get(['affectation'])!.value,
      matricule: this.editForm.get(['matricule'])!.value,
      requestId: this.editForm.get(['requestId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDerogation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IRequest): any {
    return item.id;
  }
}
