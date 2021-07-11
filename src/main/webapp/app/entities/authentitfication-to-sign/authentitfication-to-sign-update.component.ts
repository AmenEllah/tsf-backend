import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAuthentitficationToSign, AuthentitficationToSign } from 'app/shared/model/authentitfication-to-sign.model';
import { AuthentitficationToSignService } from './authentitfication-to-sign.service';

@Component({
  selector: 'jhi-authentitfication-to-sign-update',
  templateUrl: './authentitfication-to-sign-update.component.html',
})
export class AuthentitficationToSignUpdateComponent implements OnInit {
  isSaving = false;
  dateCreationDp: any;

  editForm = this.fb.group({
    id: [],
    email: [],
    token: [],
    dateCreation: [],
    valide: [],
  });

  constructor(
    protected authentitficationToSignService: AuthentitficationToSignService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ authentitficationToSign }) => {
      this.updateForm(authentitficationToSign);
    });
  }

  updateForm(authentitficationToSign: IAuthentitficationToSign): void {
    this.editForm.patchValue({
      id: authentitficationToSign.id,
      email: authentitficationToSign.email,
      token: authentitficationToSign.token,
      dateCreation: authentitficationToSign.dateCreation,
      valide: authentitficationToSign.valide,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const authentitficationToSign = this.createFromForm();
    if (authentitficationToSign.id !== undefined) {
      this.subscribeToSaveResponse(this.authentitficationToSignService.update(authentitficationToSign));
    } else {
      this.subscribeToSaveResponse(this.authentitficationToSignService.create(authentitficationToSign));
    }
  }

  private createFromForm(): IAuthentitficationToSign {
    return {
      ...new AuthentitficationToSign(),
      id: this.editForm.get(['id'])!.value,
      email: this.editForm.get(['email'])!.value,
      token: this.editForm.get(['token'])!.value,
      dateCreation: this.editForm.get(['dateCreation'])!.value,
      valide: this.editForm.get(['valide'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAuthentitficationToSign>>): void {
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
}
