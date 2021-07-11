import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IJustifRevenu, JustifRevenu } from 'app/shared/model/justif-revenu.model';
import { JustifRevenuService } from './justif-revenu.service';

@Component({
  selector: 'jhi-justif-revenu-update',
  templateUrl: './justif-revenu-update.component.html',
})
export class JustifRevenuUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    labelEN: [],
    labelFR: [],
  });

  constructor(protected justifRevenuService: JustifRevenuService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ justifRevenu }) => {
      this.updateForm(justifRevenu);
    });
  }

  updateForm(justifRevenu: IJustifRevenu): void {
    this.editForm.patchValue({
      id: justifRevenu.id,
      labelEN: justifRevenu.labelEN,
      labelFR: justifRevenu.labelFR,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const justifRevenu = this.createFromForm();
    if (justifRevenu.id !== undefined) {
      this.subscribeToSaveResponse(this.justifRevenuService.update(justifRevenu));
    } else {
      this.subscribeToSaveResponse(this.justifRevenuService.create(justifRevenu));
    }
  }

  private createFromForm(): IJustifRevenu {
    return {
      ...new JustifRevenu(),
      id: this.editForm.get(['id'])!.value,
      labelEN: this.editForm.get(['labelEN'])!.value,
      labelFR: this.editForm.get(['labelFR'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJustifRevenu>>): void {
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
